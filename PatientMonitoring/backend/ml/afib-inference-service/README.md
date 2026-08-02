# AFib Inference Service

A small FastAPI service that scores a ~10-second **two-lead** ECG window for
atrial fibrillation. It loads the trained `ECG_1D_CNN` PyTorch model (`.pth`)
once at startup and exposes a `/infer` endpoint that returns a probability in
`[0, 1]` (`0` = no AFib, `1` = AFib suspected).

The model takes input shaped `[batch, 2, 2500]` (leads `ECG1`, `ECG2`; 2500
samples = 10 s at 250 Hz) and outputs a single raw logit; the service applies a
sigmoid to produce the probability.

This service is deliberately narrow: it returns **only a probability**. The
decision about what that probability means for a patient (Suspected vs.
DiagnosedConfirmed, whether to change the recording modality, etc.) belongs to
the IBM ODM business-rule decisions in the Java backend, which combine it with
the patient's medical history and current activity.

## Where it fits

```
device (Java) --CSV slice--> IoT Hub / blob
                                   |
                     backend ingestion (reads CSV, takes last 10 s)
                                   |  POST /infer { samples: [...] }
                          THIS SERVICE (.pth) --> { afib_probability }
                                   |
              ECGAnalysisReport.addClassification(AtrialFibrillation, p)
                                   |
                     ODM rules --> HealthCondition.setStatus(...)
```

## Setup

```bash
cd backend/ml/afib-inference-service
python -m venv .venv && source .venv/bin/activate
pip install -r requirements-dev.txt
```

Place your trained weights at `models/afib_model.pth` (or set `MODEL_PATH`).

### Plug in your model

1. **Architecture** — `app/model_architecture.py` already contains the exact
   `ECG_1D_CNN`. It expects a `state_dict` (`torch.save(model.state_dict(), ...)`).
   If you instead saved the whole model or a TorchScript module, set
   `MODEL_LOAD_MODE` to `full` or `torchscript`.
2. **Preprocessing** — the defaults in `app/config.py` already match the
   training snippet: `lead_order=[ECG1, ECG2]`, `sample_rate_hz=250`,
   `window_seconds=10`, `normalization=none`, `apply_sigmoid=true`. **Only**
   change `normalization` if you normalized `X` before training (the snippet
   fed it raw).

All config values are overridable via env vars or a `.env` file.

> **Lead order matters.** Training used `X.reshape(-1, 2, 2500)`, so the first
> 2500 columns of `X` became channel 0 and the next 2500 became channel 1.
> `lead_order[0]` must be whichever lead filled those first 2500 columns.

## Run

```bash
uvicorn app.main:app --host 0.0.0.0 --port 8000
```

- `GET /health` → `{ status, model_loaded, model_version }`
- `POST /infer` → body `{ "leads": [[<ECG1>], [<ECG2>]], "sampling_rate_hz": 250 }`,
  returns `{ "afib_probability": 0.87, "model_version": "...", "window_samples": 2500 }`
- Interactive docs at `http://localhost:8000/docs`

### Example

```bash
curl -X POST http://localhost:8000/infer \
  -H 'Content-Type: application/json' \
  -d '{"leads": [[0.1, 0.2, ...], [0.05, 0.06, ...]], "sampling_rate_hz": 250}'
```

To score a device CSV slice directly, `app/csv_window.py` reads the
`,ECG1,ECG2,qrs_annotation,generic_annotation` format; use
`read_leads(path, ["ECG1", "ECG2"])` to get both leads in order.

## Test

```bash
pytest
```

The test saves a state_dict for the placeholder architecture and exercises the
full load → preprocess → predict path, so it works before the real model is in
place and continues to work after.
