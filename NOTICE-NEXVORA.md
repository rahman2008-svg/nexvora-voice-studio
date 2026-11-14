# NexVora Voice Studio: notice of modification

NexVora Voice Studio is a modified version of **VoiceStudio** (https://github.com/debpalash/VoiceStudio),
Copyright 2024-present Palash Debnath and VoiceStudio contributors, licensed under the
**GNU Affero General Public License v3.0**. The full licence is in [`LICENSE`](LICENSE) and is unchanged.
Upstream's own notice is preserved in [`LICENSE-NOTICE.md`](LICENSE-NOTICE.md) and its README in `README.upstream.md`.

New and modified work by Prince AR Abdur Rahman, publisher NexVora Lab's Ofc, is
Copyright (c) 2026 NexVora Lab's Ofc, and is distributed under the same AGPL-3.0. That copyright covers only
the NexVora-authored code and assets listed below, not upstream code. Because this is AGPL-3.0, anyone who
receives the app, or uses a modified version over a network, is entitled to the complete corresponding source.

## Changes made (see `git log`; the first commit is the untouched upstream baseline)
- App identity: `applicationId` `com.nexvora.voicestudio`, version 1.0.0 (code 100), name "NexVora Voice Studio",
  URL scheme `nexvoravoice://`. The Kotlin namespace stays `com.voicestudio.mobile` so upstream JNI symbol names remain intact.
- Removed: the content-report upload client wiring, the anonymous analytics consent dialog and toggle, the desktop-host
  connect UI and routes, community gallery, calls, handoff, local-API, donate/pro/promo screens, and camera permission.
  The code of those modules still sits in the tree, unreferenced by the UI.
- New design system: `NexVoraTokens.kt`, `NexVoraComponents.kt`; new screens and navigation: `NexScreens.kt`, `StudioNavigation.kt`.
- New icon `docs/nexvora-logo.svg`; new workflow `.github/workflows/android-apk.yml`. Upstream workflows are parked,
  unmodified, in `.github/upstream-workflows-disabled/`.
- Catalog text is rebranded at build time (VoiceStudio -> NexVora Voice Studio); upstream catalogs are untouched on disk.
