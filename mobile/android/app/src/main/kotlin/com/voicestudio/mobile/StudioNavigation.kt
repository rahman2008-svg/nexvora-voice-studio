// Modified by NexVora Lab's Ofc (c) 2026 under AGPL-3.0: five-tab NexVora navigation replacing the upstream dock.
// Upstream copyright: Palash Debnath and VoiceStudio contributors (see LICENSE-NOTICE.md).
package com.voicestudio.mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voicestudio.mobile.designsystem.*
import com.voicestudio.mobile.features.*

/** The five primary destinations; "studio" is the emphasised centre action. */
private val tabs = listOf("home", "projects", "studio", "voices", "profile")
internal fun isWorkspace(route: String) = route in tabs
/** Routes that draw their own NexVora header and therefore get no shared top bar. */
internal fun hasOwnHeader(route: String) = route == "home" || route == "studio" || route == "profile"

/** Which tab stays highlighted while a deeper screen is open. */
internal fun workspaceFor(route: String): String = when {
    route in tabs -> route
    route == "projects" || route.startsWith("project/") -> "projects"
    route.startsWith("voice/") || route.startsWith("preset/") -> "voices"
    route == "settings" -> "profile"
    route == "clone" || route == "speak" || route == "dictate" || route == "dub" || route == "tools" || route == "design" ||
        route == "audiobook" || route == "stories" || route == "batch" || route == "models" || route == "transcriptions" ||
        route == "workflows" || route == "incoming" -> "studio"
    else -> "home"
}

@Composable internal fun StudioTopBar(route: String, onBack: () -> Unit) {
    val title = when {
        route == "projects" || route.startsWith("project/") -> tr("projects.title")
        route == "voices" -> "Library"
        route == "transcriptions" -> tr("transcriptions.title")
        route == "design" -> tr("nav.design")
        route == "stories" -> tr("nav.stories")
        route == "audiobook" -> tr("audiobook.title")
        route == "batch" -> tr("batch.title")
        route == "workflows" -> tr("workflows.title")
        route == "tools" -> tr("tools.title")
        route == "incoming" -> tr("mobile.integration.received")
        route.startsWith("preset/") || route == "speak" || route.startsWith("voice/") -> tr("mobile.tabs.speak")
        route == "models" -> tr("mobile.local.models")
        route == "clone" -> tr("mobile.voices.new_clone")
        else -> tr("mobile.tabs.$route")
    }
    Row(Modifier.fillMaxWidth().statusBarsPadding().heightIn(min = 52.dp).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        if (!isWorkspace(route)) IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, tr("common.back"), tint = Color.White) }
        Text(title, Modifier.weight(1f).semantics { heading() }, color = Color.White, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable private fun RowScope.DockItem(label: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) NexVora.Cyan else NexVora.TextSecondary
    Column(Modifier.weight(1f).fillMaxHeight().selectable(selected = selected, role = Role.Tab, onClick = onClick).semantics { contentDescription = label },
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(icon, null, Modifier.size(24.dp), tint = tint)
        Text(label, color = tint, fontSize = 11.sp, maxLines = 1)
    }
}

/** Bottom navigation: Home, Projects, Studio (raised gradient action), Library, Profile. */
@Composable internal fun NexDock(selected: String, navigate: (String) -> Unit) {
    val shape = RoundedCornerShape(28.dp)
    Box(Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 16.dp, vertical = 8.dp), contentAlignment = Alignment.BottomCenter) {
        Row(Modifier.widthIn(max = 440.dp).fillMaxWidth().height(68.dp).background(NexVora.BackgroundSecondary.copy(alpha = .96f), shape)
            .border(1.dp, NexVora.BorderGradient, shape).selectableGroup(), verticalAlignment = Alignment.CenterVertically) {
            DockItem("Home", Icons.Default.Home, selected == "home") { navigate("home") }
            DockItem("Projects", VSIcons.Folder, selected == "projects") { navigate("projects") }
            Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                Box(Modifier.offset(y = (-14).dp).size(60.dp).background(NexVora.ActionGradient, CircleShape).border(2.dp, NexVora.Cyan.copy(alpha = .6f), CircleShape)
                    .selectable(selected = selected == "studio", role = Role.Tab, onClick = { navigate("studio") }).semantics { contentDescription = "Studio" },
                    contentAlignment = Alignment.Center) { Icon(Icons.Default.Add, null, Modifier.size(30.dp), tint = Color.White) }
            }
            DockItem("Library", VSIcons.Wave, selected == "voices") { navigate("voices") }
            DockItem("Profile", Icons.Default.Person, selected == "profile") { navigate("profile") }
        }
    }
}
