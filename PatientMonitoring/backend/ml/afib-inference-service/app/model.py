"""Load the trained model once and run inference on a preprocessed window."""

from pathlib import Path
from typing import List, Optional

import torch

from .config import Settings, get_settings
from .model_architecture import build_model
from .preprocessing import preprocess


class AFibModel:
    def __init__(self, settings: Settings) -> None:
        self.settings = settings
        self._model: Optional[torch.nn.Module] = None
        self._device = torch.device(settings.device)

    @property
    def is_loaded(self) -> bool:
        return self._model is not None

    def load(self) -> None:
        path = Path(self.settings.model_path)
        if not path.exists():
            raise FileNotFoundError(
                f"Model file not found at '{path.resolve()}'. Place your .pth there "
                f"or set MODEL_PATH."
            )

        mode = self.settings.model_load_mode
        if mode == "torchscript":
            model = torch.jit.load(str(path), map_location=self._device)
        elif mode == "full":
            model = torch.load(str(path), map_location=self._device, weights_only=False)
        else:  # state_dict
            model = build_model(self.settings.expected_input_length)
            state_dict = torch.load(str(path), map_location=self._device)
            # Some checkpoints wrap the weights under a key such as "state_dict".
            if isinstance(state_dict, dict) and "state_dict" in state_dict:
                state_dict = state_dict["state_dict"]
            model.load_state_dict(state_dict)

        model.to(self._device)
        model.eval()
        self._model = model

    @torch.inference_mode()
    def predict_proba(self, leads: List[List[float]], sampling_rate_hz: int) -> float:
        if self._model is None:
            raise RuntimeError("Model is not loaded.")

        tensor = preprocess(leads, sampling_rate_hz, self.settings).to(self._device)
        output = self._model(tensor)

        if self.settings.apply_sigmoid:
            output = torch.sigmoid(output)

        prob = float(output.reshape(-1)[0].item())
        # Guard against tiny numerical drift outside [0, 1].
        return max(0.0, min(1.0, prob))


_model_singleton: Optional[AFibModel] = None


def get_model() -> AFibModel:
    global _model_singleton
    if _model_singleton is None:
        _model_singleton = AFibModel(get_settings())
    return _model_singleton
