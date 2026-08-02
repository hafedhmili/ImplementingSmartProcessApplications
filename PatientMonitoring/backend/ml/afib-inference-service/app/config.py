from functools import lru_cache
from typing import List, Literal

from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    """Runtime configuration for the AFib inference service.

    Every value can be overridden with an environment variable of the same name
    (case-insensitive) or via a local ``.env`` file. The defaults match the
    ``ECG_1D_CNN`` training setup: two leads, 2500 samples each (10 s at
    250 Hz), raw (un-normalized) input, single-logit output.
    """

    model_config = SettingsConfigDict(env_file=".env", extra="ignore")

    # --- Model loading -------------------------------------------------------
    model_path: str = "models/ecg_cnn_model.pth"
    # How the .pth was produced:
    #   "state_dict"  -> torch.save(model.state_dict(), ...)  (this model)
    #   "full"        -> torch.save(model, ...)
    #   "torchscript" -> torch.jit.save(scripted_model, ...)
    model_load_mode: Literal["state_dict", "full", "torchscript"] = "state_dict"
    device: str = "cpu"
    model_version: str = "ecg-1d-cnn-0.1.0"

    # --- Signal / window spec (matches training) -----------------------------
    sample_rate_hz: int = 250
    window_seconds: float = 10.0

    # The network expects two leads in this order. Training reshaped a flat
    # 5000-vector as X.reshape(-1, 2, 2500), so the first 2500 samples became
    # channel 0 and the next 2500 became channel 1. lead_order[0] must be the
    # lead that occupied the first 2500 columns of the training data.
    lead_order: List[str] = ["ECG1", "ECG2"]

    # If the model was trained at a different rate, each lead is resampled to
    # this value before being fed in. Leave equal to sample_rate_hz to disable.
    model_sample_rate_hz: int = 250

    # --- Preprocessing (matches training) ------------------------------------
    # Training fed X straight into the model (only reshape + float32 cast), so
    # no normalization. Set to "zscore"/"minmax" ONLY if you normalized X before
    # training.
    normalization: Literal["zscore", "minmax", "none"] = "none"

    # The network outputs a raw logit (BCEWithLogitsLoss during training), so a
    # sigmoid is applied to produce a probability in [0, 1].
    apply_sigmoid: bool = True

    @property
    def num_leads(self) -> int:
        return len(self.lead_order)

    @property
    def expected_input_length(self) -> int:
        """Samples per lead the model expects after any resampling."""
        return int(round(self.model_sample_rate_hz * self.window_seconds))


@lru_cache
def get_settings() -> Settings:
    return Settings()
