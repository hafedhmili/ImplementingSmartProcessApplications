from typing import List, Optional

from pydantic import BaseModel, Field


class InferenceRequest(BaseModel):
    """A single ~10-second, multi-lead ECG window to classify.

    ``leads`` is an ordered list of per-lead amplitude series matching the
    service's ``lead_order`` (default ``[ECG1, ECG2]``). Each series may contain
    more or fewer points than expected; preprocessing trims each to the last
    ``window_seconds`` and resamples as needed.
    """

    leads: List[List[float]] = Field(
        ...,
        min_length=1,
        description="Ordered per-lead ECG series, e.g. [ECG1_samples, ECG2_samples].",
    )
    sampling_rate_hz: Optional[int] = Field(
        default=None,
        description="Sampling rate of the provided samples. Defaults to the service's configured rate.",
    )
    patient_id: Optional[str] = Field(default=None, description="Optional patient identifier for tracing.")


class InferenceResponse(BaseModel):
    afib_probability: float = Field(..., ge=0.0, le=1.0, description="0 = no AFib, 1 = AFib suspected.")
    model_version: str
    window_samples: int = Field(..., description="Samples per lead fed to the model after preprocessing.")


class HealthResponse(BaseModel):
    status: str
    model_loaded: bool
    model_version: str
