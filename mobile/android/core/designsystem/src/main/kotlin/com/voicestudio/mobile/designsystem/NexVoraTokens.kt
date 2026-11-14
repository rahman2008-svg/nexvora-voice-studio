// Copyright (c) 2026 NexVora Lab's Ofc. New NexVora code, licensed under AGPL-3.0 together with the
// upstream VoiceStudio project it extends (see LICENSE and NOTICE-NEXVORA.md).
package com.voicestudio.mobile.designsystem

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** NexVora Voice Studio brand tokens, taken from the project's design brief. */
object NexVora {
    val Background = Color(0xFF050817)
    val BackgroundSecondary = Color(0xFF0B1230)
    val Card = Color(0xFF111A3A)
    val Cyan = Color(0xFF00D5FF)
    val Blue = Color(0xFF245BFF)
    val Purple = Color(0xFF8957FF)
    val Magenta = Color(0xFFE83EFF)
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFFAAB4D0)

    /** Primary action gradient: purple -> electric blue -> cyan. */
    val ActionGradient: Brush get() = Brush.horizontalGradient(listOf(Purple, Blue, Cyan))
    /** Wordmark / waveform gradient: cyan -> purple -> magenta. */
    val NeonGradient: Brush get() = Brush.horizontalGradient(listOf(Cyan, Purple, Magenta))
    val BorderGradient: Brush get() = Brush.linearGradient(listOf(Cyan.copy(alpha = .55f), Purple.copy(alpha = .55f), Magenta.copy(alpha = .35f)))

    object Space { val xs = 4.dp; val sm = 8.dp; val md = 16.dp; val lg = 24.dp; val xl = 32.dp }
    object Radius { val card = 24.dp; val field = 16.dp; val pill = 50.dp }

    private val dark: Map<String, Long> by lazy {
        base(true) + mapOf<String, Long>(
            "canvas" to 0xFF050817, "chrome" to 0xFF050817, "toolbar" to 0xFF050817, "toolbarForeground" to 0xFFFFFFFF,
            "toolbarBorder" to 0xFF1C2656, "toolbarControl" to 0xFF111A3A, "toolbarControlForeground" to 0xFFFFFFFF, "toolbarControlHover" to 0xFF1B2766,
            "surface" to 0xFF111A3A, "surfaceRaised" to 0x14FFFFFF, "surfaceOverlay" to 0xFF0B1230,
            "text" to 0xFFFFFFFF, "textMuted" to 0xFFAAB4D0, "border" to 0xFF2A3570, "input" to 0xFF1A2550,
            "focus" to 0xFF00D5FF, "accent" to 0xFF00D5FF, "accentForeground" to 0xFF050817,
            "secondary" to 0xFF0B1230, "secondaryForeground" to 0xFFFFFFFF, "muted" to 0xFF0B1230, "mutedForeground" to 0xFFAAB4D0,
            "placeholder" to 0xFF7C88AD, "secondaryLabel" to 0xFFAAB4D0, "iconMuted" to 0xFFAAB4D0,
            "error" to 0xFFFF5C7A, "errorForeground" to 0xFFFFB3C1, "errorSurface" to 0x33FF5C7A,
            "accentSurface" to 0xFF16275F, "accentSurfaceForeground" to 0xFFFFFFFF,
            "messageSurface" to 0xFF1B2A6B, "messageForeground" to 0xFFFFFFFF,
            "messageAction" to 0xFF8957FF, "messageActionForeground" to 0xFFFFFFFF, "messageActionHover" to 0xFF245BFF,
            "codeBackground" to 0xFF0B1230, "codeForeground" to 0xFFE6EBFF,
            "sidebar" to 0xFF0B1230, "sidebarForeground" to 0xFFFFFFFF, "sidebarMutedForeground" to 0xFFAAB4D0,
            "sidebarControlSurface" to 0xFF111A3A, "sidebarRowHover" to 0xFF16275F, "sidebarRowActive" to 0xFF1B2A6B,
            "sidebarRowSelected" to 0xFF1B2A6B, "sidebarBorder" to 0xFF2A3570,
        )
    }
    private val light: Map<String, Long> by lazy {
        base(false) + mapOf<String, Long>(
            "canvas" to 0xFFF4F6FF, "chrome" to 0xFFF4F6FF, "toolbar" to 0xFFF4F6FF, "surface" to 0xFFFFFFFF,
            "text" to 0xFF0B1230, "textMuted" to 0xFF4A5578, "border" to 0xFFD5DBF5,
            "focus" to 0xFF245BFF, "accent" to 0xFF245BFF, "accentForeground" to 0xFFFFFFFF,
            "messageAction" to 0xFF245BFF, "messageActionForeground" to 0xFFFFFFFF, "messageActionHover" to 0xFF8957FF,
            "messageSurface" to 0xFFE3E8FF, "messageForeground" to 0xFF0B1230,
        )
    }
    /** Starts from the upstream default palette so every role the generated tokens define stays present. */
    private fun base(isDark: Boolean): Map<String, Long> {
        val palette = VSTokens.palettes.first { it.id == "default" }
        return (if (isDark) palette.dark else palette.light)!!
    }
    fun roles(isDark: Boolean): Map<String, Long> = if (isDark) dark else light
}
