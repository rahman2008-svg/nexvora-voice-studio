// Copyright (c) 2026 NexVora Lab's Ofc. New NexVora code, licensed under AGPL-3.0 together with the
// upstream VoiceStudio project it extends (see LICENSE and NOTICE-NEXVORA.md).
package com.voicestudio.mobile

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voicestudio.mobile.designsystem.*
import com.voicestudio.mobile.features.StudioViewModel
import com.voicestudio.mobile.features.VSIcons

private fun formatBytes(bytes: Long): String = when {
    bytes >= 1L shl 30 -> String.format(java.util.Locale.US, "%.1f GB", bytes / (1L shl 30).toDouble())
    bytes >= 1L shl 20 -> String.format(java.util.Locale.US, "%.0f MB", bytes / (1L shl 20).toDouble())
    else -> String.format(java.util.Locale.US, "%.0f KB", bytes / 1024.0)
}

@Composable private fun NexBrandMark() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(Modifier.size(40.dp).background(NexVora.NeonGradient, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
            Text("N", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        }
        Column {
            Text("NexVora", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text("Voice Studio", color = NexVora.TextSecondary, fontSize = 13.sp)
        }
    }
}

/** Home dashboard. Every control navigates to a screen that exists in the NavHost. */
@Composable internal fun NexHomeScreen(vm: StudioViewModel, navigate: (String) -> Unit) {
    val phone by vm.phone.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        try { vm.phone.refresh() } catch (e: kotlinx.coroutines.CancellationException) { throw e } catch (e: Exception) { vm.fail(e) }
    }
    val free = remember(phone.used) { context.filesDir.usableSpace }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).statusBarsPadding().padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f)) { NexBrandMark() }
            IconButton(onClick = { navigate("settings") }) { Icon(Icons.Default.Notifications, "Notification settings", tint = Color.White) }
            IconButton(onClick = { navigate("settings") }) { Icon(Icons.Default.Settings, "Settings", tint = Color.White) }
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Welcome Back!", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.semantics { heading() })
            Text("Turn your ideas into amazing audio & voice.", color = NexVora.TextSecondary, fontSize = 15.sp)
        }
        Row(Modifier.fillMaxWidth().background(NexVora.ActionGradient, RoundedCornerShape(24.dp))
            .clickable(role = Role.Button) { navigate("studio") }.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Start Creating", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Text("Explore powerful tools", color = Color.White.copy(alpha = .85f), fontSize = 13.sp)
            }
            Box(Modifier.size(40.dp).background(Color.White.copy(alpha = .22f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, null, tint = Color.White)
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NexToolTile("Voice Cloning", VSIcons.Wave, NexVora.Purple, { navigate("clone") }, Modifier.weight(1f))
            NexToolTile("Text to Speech", Icons.Default.Create, NexVora.Cyan, { navigate("speak") }, Modifier.weight(1f))
            NexToolTile("Speech Transcription", VSIcons.Mic, NexVora.Blue, { navigate("dictate") }, Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NexToolTile("Video Dubbing", VSIcons.Video, NexVora.Magenta, { navigate("dub") }, Modifier.weight(1f))
            NexToolTile("Audio Editor", Icons.Default.Build, Color(0xFFFFA24D), { navigate("tools") }, Modifier.weight(1f))
            NexToolTile("More Tools", Icons.Default.Add, NexVora.Cyan, { navigate("studio") }, Modifier.weight(1f))
        }
        NexSectionHeader("Recent Generations", "See All", { navigate("projects") })
        if (phone.recent.isEmpty()) {
            NexCard(Modifier.fillMaxWidth(), onClick = { navigate("speak") }) {
                Text("No generations yet. Tap here to turn text into speech.", color = NexVora.TextSecondary, fontSize = 14.sp)
            }
        } else phone.recent.take(3).forEach { generation ->
            NexCard(Modifier.fillMaxWidth(), onClick = { navigate("speak") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PlayArrow, null, tint = NexVora.Cyan)
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(generation.text, color = Color.White, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(DateUtils.getRelativeTimeSpanString(generation.createdAt).toString(), color = NexVora.TextSecondary, fontSize = 12.sp)
                    }
                }
            }
        }
        NexSectionHeader("Saved Voice Profiles", "See All", { navigate("voices") })
        if (phone.voices.isEmpty()) {
            NexCard(Modifier.fillMaxWidth(), onClick = { navigate("clone") }) {
                Text("No voice profiles yet. Tap here to create one.", color = NexVora.TextSecondary, fontSize = 14.sp)
            }
        } else phone.voices.take(3).forEach { voice ->
            NexCard(Modifier.fillMaxWidth(), onClick = { navigate("voices") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, null, tint = NexVora.Purple)
                    Spacer(Modifier.width(12.dp))
                    Text(voice.name, color = Color.White, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                }
            }
        }
        NexSectionHeader("Storage")
        NexCard(Modifier.fillMaxWidth(), onClick = { navigate("models") }) {
            Row { Text("Models and data", color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f)); Text("${formatBytes(phone.used)} used  /  ${formatBytes(free)} free", color = NexVora.TextSecondary, fontSize = 13.sp) }
            Spacer(Modifier.height(10.dp))
            NexProgressBar(if (phone.used + free > 0) phone.used.toFloat() / (phone.used + free) else 0f)
        }
        Spacer(Modifier.height(24.dp))
    }
}

/** Studio hub: one entry per tool that has a working screen in this build. */
@Composable internal fun NexStudioScreen(navigate: (String) -> Unit) {
    val tools: List<Triple<String, ImageVector, String>> = listOf(
        Triple("Voice Cloning", VSIcons.Wave, "clone"), Triple("Text to Speech", Icons.Default.Create, "speak"),
        Triple("Speech Transcription", VSIcons.Mic, "dictate"), Triple("Video Dubbing", VSIcons.Video, "dub"),
        Triple("Audio Editor & Converter", Icons.Default.Build, "tools"), Triple("Voice Design", Icons.Default.Star, "design"),
        Triple("Audiobook Creator", Icons.Default.Info, "audiobook"), Triple("Story Narration", Icons.Default.Edit, "stories"),
        Triple("Batch Processing", Icons.Default.Add, "batch"), Triple("Model Manager", VSIcons.Download, "models"),
    )
    val tints = listOf(NexVora.Purple, NexVora.Cyan, NexVora.Blue, NexVora.Magenta, Color(0xFFFFA24D))
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).statusBarsPadding().padding(horizontal = 20.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Studio", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.semantics { heading() })
        Text("Create • Clone • Edit • Inspire", color = NexVora.TextSecondary, fontSize = 14.sp)
        NexWaveform(Modifier.fillMaxWidth())
        tools.chunked(2).forEachIndexed { row, pair ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                pair.forEachIndexed { column, (label, icon, route) ->
                    NexToolTile(label, icon, tints[(row * 2 + column) % tints.size], { navigate(route) }, Modifier.weight(1f))
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable private fun NexSettingRow(icon: ImageVector, label: String, value: String? = null, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(role = Role.Button, onClick = onClick).padding(vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = NexVora.Cyan, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(14.dp))
        Text(label, color = Color.White, fontSize = 15.sp, modifier = Modifier.weight(1f))
        if (value != null) Text(value, color = NexVora.TextSecondary, fontSize = 13.sp)
        Icon(Icons.AutoMirrored.Filled.ArrowForward, null, tint = NexVora.TextSecondary, modifier = Modifier.size(18.dp))
    }
}

/** Profile: local display name, storage, and entry points to settings, models and licences. No accounts, nothing uploaded. */
@Composable internal fun NexProfileScreen(vm: StudioViewModel, version: String, navigate: (String) -> Unit) {
    val prefs by vm.preferences.collectAsState()
    val phone by vm.phone.state.collectAsState()
    val context = LocalContext.current
    var editing by remember { mutableStateOf(false) }
    var draft by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        try { vm.phone.refresh() } catch (e: kotlinx.coroutines.CancellationException) { throw e } catch (e: Exception) { vm.fail(e) }
    }
    val name = prefs.displayName.ifBlank { "NexVora User" }
    val free = remember(phone.used) { context.filesDir.usableSpace }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).statusBarsPadding().padding(horizontal = 20.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(role = Role.Button) { draft = prefs.displayName; editing = true }) {
            Box(Modifier.size(72.dp).border(2.dp, NexVora.NeonGradient, CircleShape).padding(6.dp).background(NexVora.Card, CircleShape), contentAlignment = Alignment.Center) {
                Text(name.take(1).uppercase(), color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.semantics { heading() })
                Text("Tap to edit display name", color = NexVora.TextSecondary, fontSize = 12.sp)
            }
            Icon(Icons.Default.Edit, "Edit display name", tint = NexVora.Cyan)
        }
        NexCard(Modifier.fillMaxWidth(), onClick = { navigate("models") }) {
            Row { Text("Storage Used", color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f)); Text("${formatBytes(phone.used)} / ${formatBytes(phone.used + free)}", color = NexVora.TextSecondary, fontSize = 13.sp) }
            Spacer(Modifier.height(10.dp))
            NexProgressBar(if (phone.used + free > 0) phone.used.toFloat() / (phone.used + free) else 0f)
            Spacer(Modifier.height(8.dp))
            Text("${phone.voices.size} saved voice profiles  •  ${phone.installed.size} installed models", color = NexVora.TextSecondary, fontSize = 12.sp)
        }
        val mode = when (prefs.mode) { "light" -> "Light"; "system" -> "System"; else -> "Dark" }
        NexCard(Modifier.fillMaxWidth(), contentPadding = 8.dp) {
            NexSettingRow(Icons.Default.Settings, "Appearance", "$mode Theme") { navigate("settings") }
            NexSettingRow(VSIcons.Globe, "Language") { navigate("settings") }
            NexSettingRow(VSIcons.Shield, "Privacy & Security") { navigate("settings") }
            NexSettingRow(VSIcons.Download, "Model Manager") { navigate("models") }
            NexSettingRow(Icons.Default.Create, "Transcript History") { navigate("transcriptions") }
            NexSettingRow(Icons.Default.Info, "About & Licenses", "v$version") { navigate("settings") }
        }
        Text("NexVora Voice Studio $version\n© 2026 NexVora Lab's Ofc. Based on VoiceStudio by Palash Debnath and contributors, AGPL-3.0.",
            color = NexVora.TextSecondary, fontSize = 12.sp)
        Spacer(Modifier.height(24.dp))
    }
    if (editing) AlertDialog(onDismissRequest = { editing = false }, containerColor = NexVora.Card,
        title = { Text("Display name", color = Color.White) },
        text = { OutlinedTextField(draft, { draft = it.take(40) }, singleLine = true) },
        confirmButton = { TextButton(onClick = { vm.preference { it.copy(displayName = draft.trim()) }; editing = false }) { Text("Save", color = NexVora.Cyan) } },
        dismissButton = { TextButton(onClick = { editing = false }) { Text("Cancel", color = NexVora.TextSecondary) } })
}
