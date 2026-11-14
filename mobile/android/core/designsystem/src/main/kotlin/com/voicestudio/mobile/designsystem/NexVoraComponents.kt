// Copyright (c) 2026 NexVora Lab's Ofc. New NexVora code, licensed under AGPL-3.0 together with the
// upstream VoiceStudio project it extends (see LICENSE and NOTICE-NEXVORA.md).
package com.voicestudio.mobile.designsystem

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

/** Full-screen NexVora backdrop: deep navy gradient with soft cyan/purple/magenta glows. */
@Composable fun NexBackdrop(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier.fillMaxSize()
        .background(Brush.verticalGradient(listOf(NexVora.Background, NexVora.BackgroundSecondary, NexVora.Background)))
        .drawBehind {
            drawCircle(Brush.radialGradient(listOf(NexVora.Purple.copy(alpha = .28f), Color.Transparent), center = Offset(size.width * .9f, size.height * .05f), radius = size.width * .8f),
                radius = size.width * .8f, center = Offset(size.width * .9f, size.height * .05f))
            drawCircle(Brush.radialGradient(listOf(NexVora.Cyan.copy(alpha = .18f), Color.Transparent), center = Offset(size.width * .05f, size.height * .95f), radius = size.width * .9f),
                radius = size.width * .9f, center = Offset(size.width * .05f, size.height * .95f))
        }) { content() }
}

/** Glass card with a neon gradient hairline. Clickable when [onClick] is given. */
@Composable fun NexCard(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null, contentPadding: Dp = 16.dp, content: @Composable ColumnScope.() -> Unit) {
    val shape = RoundedCornerShape(NexVora.Radius.card)
    val base = modifier.background(Brush.verticalGradient(listOf(Color(0x24FFFFFF), NexVora.Card.copy(alpha = .92f))), shape)
        .border(1.dp, NexVora.BorderGradient, shape)
    val clickable = if (onClick != null) base.clickable(role = Role.Button, onClick = onClick) else base
    Column(clickable.padding(contentPadding), content = content)
}

/** Primary pill button with the purple -> blue -> cyan action gradient. */
@Composable fun NexGradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, icon: ImageVector? = null, enabled: Boolean = true) {
    val shape = RoundedCornerShape(NexVora.Radius.pill)
    Box(modifier.fillMaxWidth().height(56.dp).alpha(if (enabled) 1f else .4f).background(NexVora.ActionGradient, shape)
        .clickable(enabled = enabled, role = Role.Button, onClick = onClick), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (icon != null) Icon(icon, null, Modifier.size(22.dp), tint = Color.White)
            Text(text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

/** Secondary pill button: transparent with a gradient outline. */
@Composable fun NexOutlineButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, icon: ImageVector? = null, enabled: Boolean = true) {
    val shape = RoundedCornerShape(NexVora.Radius.pill)
    Box(modifier.fillMaxWidth().height(56.dp).alpha(if (enabled) 1f else .4f).background(NexVora.BackgroundSecondary.copy(alpha = .6f), shape)
        .border(1.5.dp, NexVora.BorderGradient, shape).clickable(enabled = enabled, role = Role.Button, onClick = onClick), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (icon != null) Icon(icon, null, Modifier.size(22.dp), tint = NexVora.Cyan)
            Text(text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

/** Square-ish dashboard tile: glowing icon over a label. */
@Composable fun NexToolTile(label: String, icon: ImageVector, tint: Color, onClick: () -> Unit, modifier: Modifier = Modifier) {
    NexCard(modifier, onClick = onClick, contentPadding = 12.dp) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(44.dp).background(tint.copy(alpha = .16f), RoundedCornerShape(14.dp)), contentAlignment = Alignment.Center) {
                Icon(icon, null, Modifier.size(26.dp), tint = tint)
            }
            Text(label, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center, maxLines = 2)
        }
    }
}

@Composable fun NexSectionHeader(title: String, actionLabel: String? = null, onAction: (() -> Unit)? = null, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
        if (actionLabel != null && onAction != null) Text(actionLabel, color = NexVora.Cyan, fontSize = 13.sp, modifier = Modifier.clickable(role = Role.Button, onClick = onAction).padding(8.dp))
    }
}

/** Audio waveform bars with the neon gradient. Animates a travelling wave when [animated]; otherwise a static envelope. */
@Composable fun NexWaveform(modifier: Modifier = Modifier, animated: Boolean = true, bars: Int = 48) {
    val transition = rememberInfiniteTransition(label = "nex-wave")
    val phase by transition.animateFloat(0f, (2 * PI).toFloat(), infiniteRepeatable(tween(2400, easing = LinearEasing), RepeatMode.Restart), label = "phase")
    Canvas(modifier.fillMaxWidth().height(56.dp)) {
        val slot = size.width / bars
        val brush = Brush.horizontalGradient(listOf(NexVora.Cyan, NexVora.Purple, NexVora.Magenta))
        for (i in 0 until bars) {
            val envelope = 1f - abs(i - bars / 2f) / (bars / 2f) * .75f
            val wave = abs(sin((if (animated) phase else 0f) + i * .55f)) * .7f + .3f
            val h = (size.height * envelope * wave).coerceAtLeast(4.dp.toPx())
            drawRoundRect(brush, topLeft = Offset(i * slot + slot * .2f, (size.height - h) / 2f), size = Size(slot * .6f, h), cornerRadius = CornerRadius(slot * .3f))
        }
    }
}

@Composable fun NexLoading(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = NexVora.Cyan, trackColor = NexVora.Card) }
}

/** Thin gradient progress bar (storage, downloads). [fraction] is clamped to 0..1. */
@Composable fun NexProgressBar(fraction: Float, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(50)
    Box(modifier.fillMaxWidth().height(8.dp).background(NexVora.BackgroundSecondary, shape)) {
        Box(Modifier.fillMaxSize().drawBehind {
            val w = size.width * fraction.coerceIn(0f, 1f)
            drawRoundRect(NexVora.ActionGradient, size = Size(w, size.height), cornerRadius = CornerRadius(size.height / 2f))
        })
    }
}
