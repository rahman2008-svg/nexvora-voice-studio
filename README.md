# NexVora Voice Studio

**Create • Clone • Edit • Inspire.** On-device voice studio for Android (`com.nexvora.voicestudio`, v1.0.0).
© 2026 NexVora Lab's Ofc (new NexVora code only). Developer: Prince AR Abdur Rahman.

Based on [VoiceStudio](https://github.com/debpalash/VoiceStudio) (AGPL-3.0, Palash Debnath and contributors).
This project is AGPL-3.0 too: see `LICENSE`, `NOTICE-NEXVORA.md`, `THIRD_PARTY_NOTICES.md`, `MODEL_LICENSES.md`.

## Status: read this first
This repository was transformed without access to Android SDK, Gradle or the network, so **it has never been compiled,
tested or run.** Nothing here is claimed to work until the GitHub Actions workflow produces an APK and the app is tried on a device.
See [`FEATURE_MATRIX.md`](FEATURE_MATRIX.md) for the per-feature status and [`ENGINE_VERIFICATION.md`](ENGINE_VERIFICATION.md) for what was and wasn't verified.

## Privacy and AI
All inference runs on the phone (sherpa-onnx, omnivoice.cpp, llama.cpp). No AI API, no cloud inference, no analytics, no accounts.
The only network use is downloading models you choose in the Model Manager (pinned sources, SHA-256 checked).

## Build
`.github/workflows/android-apk.yml` builds `app-debug.apk` and `app-release.apk` (manual dispatch, push to `main`, or `v*` tags) and can attach them to a GitHub Release.
Optional secrets for a stable signing key: `NEXVORA_ANDROID_KEYSTORE_BASE64`, `NEXVORA_ANDROID_KEYSTORE_PASSWORD`, `NEXVORA_ANDROID_KEY_ALIAS`, `NEXVORA_ANDROID_KEY_PASSWORD`.
Without them a throwaway key is used. Local build: JDK 21, Android SDK 36, NDK 27.1.12297006, CMake 3.22.1, then `cd mobile/android && ./gradlew assembleDebug`.

## Layout
`mobile/android` Android app (Gradle, Kotlin, Compose) · `mobile/native` pinned native runtime builds · `mobile/contract` model catalog and strings ·
the rest is the preserved upstream monorepo (Python backend, Electron desktop), unused by the Android app. Upstream README: `README.upstream.md`.
