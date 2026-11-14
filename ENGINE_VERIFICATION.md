# Local AI engine verification

Method: static inspection of the repository only. No build, no inference was run (no Android SDK/NDK, no network in the authoring environment).

| Engine | Used for | What was verified by reading | Not verified |
|---|---|---|---|
| sherpa-onnx 1.13.8 (prebuilt AAR) | TTS (Kitten, Kokoro, Piper, Supertonic, Pocket), ASR (Whisper, Zipformer), ZipVoice clone | Pinned URL + SHA-256 in `mobile/native/pins.json`; `fetch.py` checks the hash; Kotlin calls `com.k2fsa.sherpa.onnx` | That the AAR contains arm64-v8a libs; that any model loads or runs |
| omnivoice.cpp @040c8b3 (built from source) | Voice cloning and design | JNI symbols in `omni.cpp` match `NativeBridge` (`cloneAudio`, `designAudio`); CMake/NDK script builds arm64-v8a + x86_64; sources checksum-pinned | Source not in the ZIP (fetched at build); that it compiles; runs |
| llama.cpp b11716 (built from source) | Translation (Qwen3 1.7B) | JNI `translateText`/`llamaDevices` match; pinned commit + checksums | Same as above |
| LAME 3.100 (vendored) | MP3 export | Sources are in the repo | Compiles/encodes |

Findings that affect the product brief:
- **Whisper.cpp is not used**; speech recognition goes through sherpa-onnx (Whisper tiny/base int8 and a streaming Zipformer).
- **Bengali:** no TTS or speech-recognition model in the catalog lists Bengali (`bn`). Only the Qwen3 translation model does.
  Bengali TTS/transcription is therefore BLOCKED until a suitable licensed model is added to `models.json` and tested.
- **Voice cloning on a 6 GB phone:** the strongest cloner (OmniVoice, 11 languages, no Bengali) declares **8 GB RAM minimum** and is
  CC-BY-NC (non-commercial). The lighter cloners are Pocket-TTS (English, non-commercial) and ZipVoice (Chinese/English). Translation (Qwen3) declares 6 GB.
- Model terms differ (several non-commercial or conditional): see `MODEL_LICENSES.md`.
