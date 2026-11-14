# Feature completion matrix

Legend: **NOT TESTED** = code exists (mostly upstream), never built or run. **NEW, NOT TESTED** = written for NexVora, never compiled.
**BLOCKED** = external dependency. **NOT IMPLEMENTED** = absent. No row is "complete and tested": nothing has been executed.

| Area | Status | Notes |
|---|---|---|
| Original repo analysis | Done (static) | AGPL-3.0; Android client in `mobile/android`; see ENGINE_VERIFICATION.md |
| Identity (`com.nexvora.voicestudio`, 1.0.0/100, name, scheme) | NEW, NOT TESTED | Kotlin namespace intentionally unchanged |
| Cloud/analytics removal | NEW, NOT TESTED | Report client unwired, analytics dialog/toggle, host-connect, gallery, calls, handoff, promos removed from UI |
| NexVora design tokens + components | NEW, NOT TESTED | Dark and light roles; upstream palettes still selectable in Settings |
| Home / Studio hub / Profile screens | NEW, NOT TESTED | Notification bell opens Settings (no notification centre yet) |
| Five-tab navigation with raised Studio action | NEW, NOT TESTED | Upstream tests for the old dock probably need updating |
| Voice cloning | NOT TESTED | Upstream engines; consent step exists; no Bengali; 8 GB RAM for OmniVoice |
| Text to speech | NOT TESTED | Upstream; English models; no Bengali |
| Speech transcription (+TXT/SRT/VTT) | NOT TESTED | Upstream via sherpa-onnx Whisper; no Bengali model |
| Video dubbing | NOT TESTED | Upstream (Media3/MediaCodec, no FFmpeg) |
| Audio editor | PARTIAL | Upstream waveform editor + converter; EQ/reverb/echo/noise reduction/undo not found |
| Audiobook, story narration, batch | NOT TESTED | Upstream screens |
| Podcast Studio, multitrack | NOT IMPLEMENTED | No tile is shown for it |
| Model manager | NOT TESTED | Upstream: download, pause/resume, SHA-256, licences |
| Projects, voice library | NOT TESTED | Upstream file-based stores, not Room; rename/duplicate/search not checked |
| Localisation of new NexVora screens | PARTIAL | New strings are English literals |
| Offline operation | NOT TESTED | Design is local-only; unverified on device |
| Unit/integration tests | NOT RUN | Upstream tests exist; design-token and navigation tests may fail after changes |
| Launcher icon | NEW, NOT TESTED | `docs/nexvora-logo.svg` fed to the upstream generator |
| GitHub Actions APK workflow | NEW, NOT RUN | Never executed |
| GitHub publishing | NOT DONE | No GitHub access here |
| APK | DOES NOT EXIST | Produced only by a successful workflow run |
