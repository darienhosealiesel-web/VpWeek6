package com.darien.week6vp.Model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Community(
    val id: String,
    val nama: String,
    val subtitle: String,
    val description: String,
    val MemberTotal: Int,
    val icon: ImageVector,
    val color: Color,
    val joined: Boolean = false
)