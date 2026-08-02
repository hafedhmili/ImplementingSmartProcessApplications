"""Model architecture definition.

This is the exact ``ECG_1D_CNN`` used during training. Because the ``.pth`` was
saved as a ``state_dict`` (the common case:
``torch.save(model.state_dict(), path)``), PyTorch needs this class to
reconstruct the network before loading the weights.

Notes about this architecture:
- Input is ``[batch, 2, 2500]`` (2 leads, 2500 samples = 10 s at 250 Hz).
- The final layer outputs a single raw **logit** (no sigmoid); the service
  applies a sigmoid to turn it into a probability (``apply_sigmoid=True``).
- The flattened size ``8000 = 64 * 125`` hard-codes the 2500-sample window, so
  the input length must stay 2500.
"""

import torch
from torch import nn


class ECG_1D_CNN(nn.Module):
    def __init__(self) -> None:
        super().__init__()

        self.conv_layers = nn.Sequential(
            nn.Conv1d(in_channels=2, out_channels=16, kernel_size=7, padding=3),
            nn.ReLU(),
            nn.MaxPool1d(kernel_size=2),  # 2500 -> 1250

            nn.Conv1d(in_channels=16, out_channels=32, kernel_size=5, padding=2),
            nn.ReLU(),
            nn.MaxPool1d(kernel_size=2),  # 1250 -> 625

            nn.Conv1d(in_channels=32, out_channels=64, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.MaxPool1d(kernel_size=5),  # 625 -> 125
        )

        self.fc_layers = nn.Sequential(
            nn.Flatten(),
            nn.Linear(8000, 128),  # 64 channels * 125 steps
            nn.ReLU(),
            nn.Dropout(0.5),
            nn.Linear(128, 1),  # single raw logit
        )

    def forward(self, x: torch.Tensor) -> torch.Tensor:
        x = self.conv_layers(x)
        x = self.fc_layers(x)
        return x


def build_model(input_length: int = 2500) -> nn.Module:
    """Return a fresh (untrained) instance of the architecture.

    ``input_length`` must be 2500 for this network; it is accepted only to keep
    a uniform factory signature.
    """
    if input_length != 2500:
        raise ValueError(
            f"ECG_1D_CNN is fixed to a 2500-sample window; got input_length={input_length}."
        )
    return ECG_1D_CNN()
