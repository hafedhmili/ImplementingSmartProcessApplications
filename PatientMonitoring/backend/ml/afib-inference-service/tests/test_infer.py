"""End-to-end test of the inference pipeline using the placeholder architecture.

It saves a state_dict for the stand-in ``AFibNet`` so the full load ->
preprocess -> predict path is exercised without the real trained weights.
Once you drop in your real architecture and .pth, the same test still applies.
"""

import importlib

import torch
from fastapi.testclient import TestClient


def _make_client(tmp_path, monkeypatch):
    from app.model_architecture import build_model

    model_path = tmp_path / "afib_model.pth"
    torch.save(build_model(2500).state_dict(), model_path)

    monkeypatch.setenv("MODEL_PATH", str(model_path))
    monkeypatch.setenv("MODEL_LOAD_MODE", "state_dict")

    # Rebuild modules so cached settings/singletons pick up the env vars.
    import app.config as config

    config.get_settings.cache_clear()
    import app.model as model_module

    importlib.reload(model_module)
    import app.main as main

    importlib.reload(main)
    return TestClient(main.app)


def test_health_and_infer(tmp_path, monkeypatch):
    with _make_client(tmp_path, monkeypatch) as client:
        health = client.get("/health")
        assert health.status_code == 200
        assert health.json()["model_loaded"] is True

        # Two leads, 12 s each at 250 Hz; preprocessing trims to the last 10 s.
        lead1 = [0.1 * (i % 20) for i in range(3000)]
        lead2 = [0.05 * (i % 15) for i in range(3000)]
        resp = client.post("/infer", json={"leads": [lead1, lead2]})
        assert resp.status_code == 200

        body = resp.json()
        assert 0.0 <= body["afib_probability"] <= 1.0
        assert body["window_samples"] == 2500

        # Wrong number of leads is a client error.
        bad = client.post("/infer", json={"leads": [lead1]})
        assert bad.status_code == 422
