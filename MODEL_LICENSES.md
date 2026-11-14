# Model licences

NexVora Voice Studio ships **no model weights** in the APK. Models are downloaded on request, from the sources listed below,
after the user confirms, and verified against the SHA-256 hashes pinned in `mobile/contract/models.json`.
This table is generated from that catalog. It summarises the declared terms; it is **not legal advice**, and each model keeps its own licence.
Several models are non-commercial or carry additional use restrictions. Check the linked terms before any commercial use of the app's output.

| Model | Capability | Runtime | Min RAM | Size | Declared licence | Category | Commercial inference | Source |
|---|---|---|---|---|---|---|---|---|
| Kitten (`kitten-mini-en-fp16`) | tts | sherpa-onnx | 2 GB | 167 MB | apache-2.0; gpl-3.0 | conditional | unknown | https://huggingface.co/csukuangfj/kitten-mini-en-v0_1-fp16 |
| Kokoro (`kokoro-multi-int8`) | tts | sherpa-onnx | 3 GB | 177 MB | apache-2.0; gpl-3.0 | conditional | unknown | https://huggingface.co/csukuangfj/kokoro-int8-multi-lang-v1_1 |
| Whisper Tiny (`whisper-tiny-int8`) | asr | sherpa-onnx | 2 GB | 104 MB | NOASSERTION | unidentified | unknown | https://huggingface.co/csukuangfj/sherpa-onnx-whisper-tiny |
| Whisper Base (`whisper-base-int8`) | asr | sherpa-onnx | 3 GB | 161 MB | NOASSERTION | unidentified | unknown | https://huggingface.co/csukuangfj/sherpa-onnx-whisper-base |
| Zipformer (`zipformer-en-20m-int8`) | asr-stream | sherpa-onnx | 2 GB | 44 MB | apache-2.0 | commercial-ok | unknown | https://huggingface.co/csukuangfj/sherpa-onnx-streaming-zipformer-en-20M-2023-02-17 |
| OmniVoice (`omnivoice-q4-k-m`) | clone | omnivoice.cpp | 8 GB | 1391 MB | CC-BY-NC-4.0; Higgs Audio 2; Meta Llama 3 | non-commercial | restricted | https://huggingface.co/Serveurperso/OmniVoice-GGUF |
| Qwen3 1.7B (`qwen3-1-7b-q4-k-m`) | translate | llama.cpp | 6 GB | 1107 MB | apache-2.0 | commercial-ok | unknown | https://huggingface.co/unsloth/Qwen3-1.7B-GGUF |
| Piper (`piper-lessac-en-medium`) | tts | sherpa-onnx | 2 GB | 64 MB | GPL-3.0; NOASSERTION | unidentified | unknown | https://huggingface.co/csukuangfj/vits-piper-en_US-lessac-medium |
| Supertonic 3 (`supertonic3-int8`) | tts | sherpa-onnx | 3 GB | 145 MB | OpenRAIL-M; MIT | conditional | unknown | https://huggingface.co/csukuangfj2/sherpa-onnx-supertonic-3-tts-int8-2026-05-11 |
| Pocket TTS (`pocket-tts-int8-en`) | clone | sherpa-onnx | 3 GB | 198 MB | CC-BY-4.0; non-commercial conversion restriction | non-commercial | restricted | https://huggingface.co/csukuangfj2/sherpa-onnx-pocket-tts-int8-2026-01-26 |
| ZipVoice (`zipvoice-distill-int8-zh-en`) | clone | sherpa-onnx | 3 GB | 187 MB | Apache-2.0; MIT; GPL-3.0 | conditional | unknown | https://huggingface.co/k2-fsa/ZipVoice |

## Credits

- **Kitten**: KittenML; ONNX conversion by Fangjun Kuang; eSpeak NG contributors
- **Kokoro**: hexgrad / Kokoro contributors; ONNX conversion by Fangjun Kuang; eSpeak NG contributors
- **Whisper Tiny**: csukuangfj/sherpa-onnx-whisper-tiny
- **Whisper Base**: OpenAI; sherpa-onnx conversion by Fangjun Kuang
- **Zipformer**: csukuangfj/sherpa-onnx-streaming-zipformer-en-20M-2023-02-17
- **OmniVoice**: OmniVoice — Han Zhu and coauthors; GGUF conversion by Serveurperso; tokenizer by Boson AI / Meta
- **Qwen3 1.7B**: Alibaba Cloud / Qwen Team; GGUF quantization by Unsloth AI
- **Piper**: Piper / Rhasspy contributors; Lessac Blizzard 2013; eSpeak NG contributors
- **Supertonic 3**: Supertone Inc.; ONNX conversion by Fangjun Kuang
- **Pocket TTS**: Kyutai; ONNX export by KevinAHM; conversion packaging by Fangjun Kuang
- **ZipVoice**: ZipVoice / k2-fsa authors; Vocos authors; eSpeak NG contributors

## Notes

- Voice cloning requires explicit consent from the voice's owner; the app asks for it before a profile is created.
- Models flagged `requires_acceptance` show their terms in-app and must be accepted before use.
- Source of truth: `mobile/contract/models.json` and `docs/model-credits.md` (upstream).
