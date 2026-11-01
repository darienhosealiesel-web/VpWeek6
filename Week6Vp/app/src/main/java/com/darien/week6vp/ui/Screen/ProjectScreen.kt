package com.darien.week6vp.ui.Screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.darien.week6vp.ui.Widgets.PopUp
import com.darien.week6vp.ui.Widgets.TechStackRow
import com.darien.week6vp.ui.Widgets.AppBar
import com.darien.week6vp.ViewModel.ProjekUiState
import com.darien.week6vp.ViewModel.ProjectFormState
import com.darien.week6vp.ui.Widgets.PopUp

@Composable
fun ProjectsScreen(
    communityId: String,
    uiState: ProjekUiState,
    form: ProjectFormState,
    techOptions: List<String>,
    onBack: () -> Unit,
    onProjectClick: (projectId: String) -> Unit,
    onDelete: (projectId: String) -> Unit,
    onAddClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDeskripsiChange: (String) -> Unit,
    onTechToggle: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val listState = rememberLazyListState()

    val community = uiState.findCommunity(communityId)
    val projects = uiState.projectsIn(communityId)

    val blue = Color(0xFF2F5BEA)
    val red = Color(0xFFD32F2F)
    val accent = community?.color ?: blue
    val border = BorderStroke(1.dp, Color(0xFFE5E7EB))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FC))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppBar(
                title = "${community?.nama.orEmpty()} Projects",
                subtitle = community?.subtitle,
                onBack = onBack
            )
            HorizontalDivider(color = Color(0xFFE5E7EB))

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 88.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = accent.copy(alpha = 0.1f)),
                        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    community?.subtitle.orEmpty(),
                                    fontSize = 16.sp,
                                    lineHeight = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    "${community?.MemberTotal ?: 0} Anggota • ${projects.size} Proyek Aktif",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = accent
                                )
                            }
                            Text(
                                "Joined ✓",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = red,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White)
                                    .border(1.dp, red.copy(alpha = 0.5f), RoundedCornerShape(50))
                                    .padding(horizontal = 12.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Outlined.ContentCopy,
                            contentDescription = null,
                            tint = Color(0xFF1E3A8A),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            "Daftar Proyek Mahasiswa (${projects.size})",
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                if (projects.isEmpty()) {
                    item {
                        Text(
                            "Belum ada proyek yang dicatat",
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            color = Color.Gray
                        )
                    }
                }

                items(projects, key = {it.id}) {project ->
                    Card(
                        onClick = {onProjectClick(project.id)},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = border
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.Top) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(project.color.copy(alpha = 0.12f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        project.icon,
                                        contentDescription = null,
                                        tint = project.color,
                                        modifier = Modifier.size(26.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 12.dp)
                                ) {
                                    Text(
                                        project.title,
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        project.description,
                                        fontSize = 13.sp,
                                        lineHeight = 17.sp,
                                        color = Color.Gray,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFDECEC))
                                        .clickable {onDelete(project.id)},
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Remove,
                                        contentDescription = "Hapus",
                                        tint = red,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            TechStackRow(items = project.tech)

                            HorizontalDivider(color = Color(0xFFE5E7EB))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "ID: ${project.id}",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.weight(1f)
                                )
                                val statusText = if (project.completed) Color(0xFF15803D) else Color(0xFFB45309)
                                val statusBg = if (project.completed) Color(0xFFDCFCE7) else Color(0xFFFEF3C7)
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(statusBg)
                                        .padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(statusText)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        if (project.completed) "Completed" else "In Progress",
                                        fontSize = 11.sp,
                                        lineHeight = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = statusText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = onAddClick,
            icon = {Icon(Icons.Filled.Add, contentDescription = null)},
            text = {Text("Add Project", fontWeight = FontWeight.Bold)},
            containerColor = Color(0xFFDCE6FF),
            contentColor = blue,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

        if (form.show) {
            PopUp(
                communityName = "${community?.nama.orEmpty()} (${community?.subtitle.orEmpty()})",
                form = form,
                techOptions = techOptions,
                onTitleChange = onTitleChange,
                onDescriptionChange = onDeskripsiChange,
                onTechToggle = onTechToggle,
                onSave = onSave,
                onCancel = onCancel
            )
        }
    }
}

