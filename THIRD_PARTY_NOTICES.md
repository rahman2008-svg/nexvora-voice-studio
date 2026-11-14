# Third-party notices

Full licence texts ship inside the app (Profile -> About & Licenses) from
`mobile/android/app/src/main/assets/licenses/`, and the native-source references are verified at build time by
`mobile/native/android/verify-notices.py`. Summary of components used by the Android app (from upstream's pinned build):

| Component | Use | Licence (see the shipped text) |
|---|---|---|
| VoiceStudio (Palash Debnath and contributors) | Base application | AGPL-3.0 |
| sherpa-onnx v1.13.8 (+ ONNX Runtime, kaldi-native-fbank, kaldi-decoder, kaldifst, OpenFst, kissfft, simple-sentencepiece, hclust-cpp, nlohmann json, Eigen) | TTS and speech recognition | Apache-2.0 / MIT / MPL-2.0 per component |
| espeak-ng and piper-phonemize (inside sherpa-onnx) | Phonemisation | GPL-3.0: corresponding source in `native-Corresponding-source.txt` |
| omnivoice.cpp + its ggml fork | Voice cloning/design runtime | MIT |
| llama.cpp b11716 + ggml | On-device translation runtime | MIT |
| LAME 3.100 (encoder only, vendored) | MP3 export | LGPL-2.0+ (see `lame.txt`) |
| Apache PDFBox for Android, Bouncy Castle | Document import | Apache-2.0 / MIT-style |
| Inter variable font | UI typeface | SIL OFL-1.1 (`OFL.txt`) |
| AndroidX, Jetpack Compose, Media3, Kotlin, kotlinx, OkHttp, ZXing | Platform libraries | Apache-2.0 |

This table is a summary compiled from upstream's notice files and Gradle/pins configuration; it was not independently audited.
Model licences are separate: see [`MODEL_LICENSES.md`](MODEL_LICENSES.md).
