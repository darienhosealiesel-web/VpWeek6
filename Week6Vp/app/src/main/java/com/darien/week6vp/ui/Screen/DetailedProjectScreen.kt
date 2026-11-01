package com.darien.week6vp.ui.Screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darien.week6vp.Data.ProjectRepository
import com.darien.week6vp.ui.Widgets.AppBar
import com.darien.week6vp.ViewModel.ProjekUiState

@Composable
fun ProjectDetailsScreen(
    projectId: String,
    uiState: ProjekUiState,
    onToggleCompleted: (projectId: String) -> Unit,
    onBack: () -> Unit
) {
    val project = uiState.findProject(projectId)
    val community = project?.let { uiState.findCommunity(it.communityId) }

    val cardBorder = BorderStroke(1.dp, Color(0xFFE5E7EB))
    val cardShape = RoundedCornerShape(20.dp)
    val accent = community?.color ?: Color(0xFF2F5BEA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FC))
    ) {
        AppBar(title = "Project Detail", onBack = onBack)

        if (project == null) {
            Text("Proyek tidak ditemukan.", modifier = Modifier.padding(16.dp))
        } else {
            val statusColor = if (project.completed) Color(0xFF15803D) else Color(0xFFB45309)
            val statusBg = if (project.completed) Color(0xFFDCFCE7) else Color(0xFFFEF3C7)
            val statusText = if (project.completed) "Completed" else "In Progress"

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(cardShape)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF1E293B), Color(0xFF0F172A))
                            )
                        )
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = community?.nama.orEmpty(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = accent,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(accent.copy(alpha = 0.2f))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                    Text(
                        text = project.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(cardShape)
                        .background(Color.White)
                        .border(cardBorder, cardShape)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Mark as Done", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Status:", fontSize = 12.sp, color = Color.Gray)
                            Spacer(Modifier.width(6.dp))
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(statusBg)
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(statusColor)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = statusText,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = statusColor
                                )
                            }
                        }
                    }
                    Switch(
                        checked = project.completed,
                        onCheckedChange = {onToggleCompleted(project.id)}
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(cardShape)
                        .background(Color.White)
                        .border(cardBorder, cardShape)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Deskripsi Lengkap Proyek", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = project.description,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = Color(0xFF475569)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(cardShape)
                        .background(Color.White)
                        .border(cardBorder, cardShape)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Tech Stack Terpilih", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Layout(
                        content = {
                            project.tech.forEach { tech ->
                                val techIcon = ProjectRepository.iconForTech(tech)
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(Color(0xFFF1F5F9))
                                        .border(BorderStroke(1.dp, Color(0xFFE2E8F0)), RoundedCornerShape(50))
                                        .padding(horizontal = 12.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = techIcon,
                                        contentDescription = null,
                                        tint = accent,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        text = tech,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF334155)
                                    )
                                }
                            }
                        }
                    ) { measurables, constraints ->
                        val spacing = 8.dp.roundToPx()
                        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0)) }

                        val positions = mutableListOf<Pair<Int, Int>>()
                        var x = 0
                        var y = 0
                        var rowHeight = 0
                        placeables.forEach { p ->
                            if (x > 0 && x + p.width > constraints.maxWidth) {
                                x = 0
                                y += rowHeight + spacing
                                rowHeight = 0
                            }
                            positions.add(x to y)
                            x += p.width + spacing
                            rowHeight = maxOf(rowHeight, p.height)
                        }

                        val totalHeight = if (placeables.isEmpty()) 0 else y + rowHeight
                        layout(constraints.maxWidth, totalHeight) {
                            placeables.forEachIndexed { i, p ->
                                val (px, py) = positions[i]
                                p.placeRelative(px, py)
                            }
                        }
                    }
                }
            }
        }
    }
}
