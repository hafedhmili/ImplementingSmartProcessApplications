"""Turn a raw multi-lead ECG window into the tensor the model was trained on.

The ECG_1D_CNN expects ``[batch, 2, 2500]``: two leads, 2500 samples each. The
steps here mirror the training pipeline (reshape + float32 cast, no
normalization by default). Adjust to match if your training did more.
"""

from typing import List, Sequence

import numpy as np
import torch
from scipy.signal import resample

from .config import Settings


def _resample_if_needed(signal: np.ndarray, source_rate: int, target_rate: int) -> np.ndarray:
    if source_rate == target_rate:
        return signal
    target_len = int(round(len(signal) * target_rate / source_rate))
    return resample(signal, target_len)


def _take_last_window(signal: np.ndarray, target_len: int) -> np.ndarray:
    """Keep the most recent ``target_len`` samples, left-padding if too short."""
    if len(signal) >= target_len:
        return signal[-target_len:]
    pad = np.zeros(target_len - len(signal), dtype=signal.dtype)
    return np.concatenate([pad, signal])


def _normalize(signal: np.ndarray, mode: str) -> np.ndarray:
    if mode == "zscore":
        std = signal.std()
        return (signal - signal.mean()) / (std if std > 1e-8 else 1.0)
    if mode == "minmax":
        lo, hi = signal.min(), signal.max()
        span = hi - lo
        return (signal - lo) / (span if span > 1e-8 else 1.0)
    return signal


def _prepare_lead(samples: Sequence[float], sampling_rate_hz: int, settings: Settings) -> np.ndarray:
    signal = np.asarray(samples, dtype=np.float32)
    signal = _resample_if_needed(signal, sampling_rate_hz, settings.model_sample_rate_hz)
    signal = _take_last_window(signal, settings.expected_input_length)
    return _normalize(signal, settings.normalization).astype(np.float32)


def preprocess(leads: List[List[float]], sampling_rate_hz: int, settings: Settings) -> torch.Tensor:
    """Build a ``[1, num_leads, expected_input_length]`` tensor from raw leads."""
    if len(leads) != settings.num_leads:
        raise ValueError(
            f"Expected {settings.num_leads} leads ({settings.lead_order}), got {len(leads)}."
        )

    processed = np.stack(
        [_prepare_lead(lead, sampling_rate_hz, settings) for lead in leads],
        axis=0,
    )  # shape: (num_leads, expected_input_length)

    return torch.from_numpy(processed).unsqueeze(0)  # (1, num_leads, length)
