"""Helpers for reading the device's ECG CSV format.

The heart-monitoring device writes slices with the header:
    ,ECG1,ECG2,qrs_annotation,generic_annotation
at ``ECG_FREQUENCY`` (250) Hz. These helpers extract the leads so a window can
be sent to ``/infer``. They are handy for local testing and for the backend
ingestion component that will call this service.
"""

import csv
from pathlib import Path
from typing import List, Sequence


def read_lead(csv_path: str | Path, lead: str = "ECG1") -> List[float]:
    """Return every sample of a single ``lead`` from a device ECG CSV file."""
    return read_leads(csv_path, [lead])[0]


def read_leads(csv_path: str | Path, leads: Sequence[str]) -> List[List[float]]:
    """Return per-lead sample series (in the given order) from a device CSV.

    Rows where any requested lead is blank are skipped, keeping the leads
    aligned sample-for-sample.
    """
    series: List[List[float]] = [[] for _ in leads]
    with open(csv_path, newline="") as fh:
        reader = csv.DictReader(fh)
        fields = reader.fieldnames or []
        missing = [lead for lead in leads if lead not in fields]
        if missing:
            raise KeyError(f"Leads {missing} not found. Available columns: {fields}")
        for row in reader:
            values = [row[lead] for lead in leads]
            if any(v is None or v == "" for v in values):
                continue
            for i, v in enumerate(values):
                series[i].append(float(v))
    return series


def last_window(samples: List[float], sample_rate_hz: int, window_seconds: float) -> List[float]:
    """Return the trailing ``window_seconds`` of ``samples``."""
    n = int(round(sample_rate_hz * window_seconds))
    return samples[-n:]
