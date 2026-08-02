"""FastAPI service that scores a 10-second ECG window for atrial fibrillation.

The service intentionally returns only a probability in [0, 1]. Deciding what
that probability *means* for a patient (Suspected vs. DiagnosedConfirmed,
whether to escalate the recording modality, etc.) is left to the IBM ODM
business-rule decisions in the Java backend, which combine it with the
patient's medical history and current activity.
"""

import logging
from contextlib import asynccontextmanager

from fastapi import FastAPI, HTTPException

from .config import get_settings
from .model import get_model
from .schemas import HealthResponse, InferenceRequest, InferenceResponse

logger = logging.getLogger("afib_inference")


@asynccontextmanager
async def lifespan(app: FastAPI):
    model = get_model()
    try:
        model.load()
        logger.info("Loaded AFib model '%s'", model.settings.model_version)
    except FileNotFoundError as exc:
        # Start anyway so /health and docs work; /infer will 503 until the
        # model file is provided.
        logger.warning("%s", exc)
    yield


app = FastAPI(
    title="AFib Inference Service",
    version=get_settings().model_version,
    lifespan=lifespan,
)


@app.get("/health", response_model=HealthResponse)
def health() -> HealthResponse:
    model = get_model()
    return HealthResponse(
        status="ok",
        model_loaded=model.is_loaded,
        model_version=model.settings.model_version,
    )


@app.post("/infer", response_model=InferenceResponse)
def infer(request: InferenceRequest) -> InferenceResponse:
    model = get_model()
    if not model.is_loaded:
        raise HTTPException(status_code=503, detail="Model not loaded. Check MODEL_PATH.")

    settings = model.settings
    sampling_rate = request.sampling_rate_hz or settings.sample_rate_hz
    try:
        probability = model.predict_proba(request.leads, sampling_rate)
    except ValueError as exc:
        raise HTTPException(status_code=422, detail=str(exc))

    return InferenceResponse(
        afib_probability=probability,
        model_version=settings.model_version,
        window_samples=settings.expected_input_length,
    )
