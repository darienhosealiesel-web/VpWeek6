package com.darien.week6vp.Data

import kotlin.collections.filter
import kotlin.collections.map

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.darien.week6vp.Model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ProjectRepository {
    val options = listOf(
        "Kotlin", "Jetpack Compose", "Firebase", "Ktor",
        "Room", "Python", "Google Maps SDK"
    )

    private val styles = listOf(
        Icons.Filled.Android to Color(0xFF2F5BEA),
        Icons.Filled.Code to Color(0xFF16A34A),
        Icons.Filled.Cloud to Color(0xFF7C3AED)
    )
    private val techIcons: Map<String, ImageVector> = mapOf(
        "Kotlin" to Icons.Filled.Code,
        "Jetpack Compose" to Icons.Filled.Widgets,
        "Firebase" to Icons.Filled.Whatshot,
        "Ktor" to Icons.Filled.Cloud,
        "Room" to Icons.Filled.Storage,
        "Python" to Icons.Filled.Settings,
        "Google Maps SDK" to Icons.Filled.Place
    )

    fun iconForTech(tech: String): ImageVector =
        techIcons[tech] ?: Icons.Filled.Build
    private val counters = mutableMapOf<String, Int>()
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun addProject(
        communityId: String,
        title: String,
        deskripsi: String,
        tech: List<String>
    ) {
        val (icon, color) = styles.random()
        val next = (counters[communityId] ?: 0) + 1
        counters[communityId] = next
        val project = Project(
            id = "proj-$communityId-${next.toString().padStart(2, '0')}",
            communityId = communityId,
            title = title,
            deskripsi = deskripsi,
            tech = tech,
            icon = icon,
            color = color
        )
        _projects.update {it + project}
    }

    fun deleteProject(projectId: String) {
        _projects.update {list -> list.filter {it.id != projectId}}
    }

    fun toggleStatus(projectId: String) {
        _projects.update {list ->
            list.map {if (it.id == projectId) it.copy(completed = !it.completed) else it}
        }
    }
}