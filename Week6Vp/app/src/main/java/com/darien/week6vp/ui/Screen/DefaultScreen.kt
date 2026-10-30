package com.darien.week6vp.ui.Screen.DefaultScreen

import com.darien.week6vp.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darien.week6vp.Model.Student
import com.darien.week6vp.ViewModel.ProjekUiState



@Composable
fun HomeScreen(
    student: Student,
    uiState: ProjekUiState
) {
    val profileImage = R.drawable.profile

    val blue = Color(0xFF2F5BEA)
    val border = BorderStroke(1.dp, Color(0xFFE5E7EB))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FC))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("CampusTech Hub", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Student Profile & Portfolio Dashboard", fontSize = 11.sp, color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3F3DB5)),
                contentAlignment = Alignment.Center
            ) {
                if (profileImage != null) {
                    Image(
                        painter = painterResource(profileImage),
                        contentDescription = "Foto profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text(
                        student.inisial,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        HorizontalDivider(color = Color(0xFFE5E7EB))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF2B4BB8))
                    .padding(vertical = 18.dp, horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImage != null) {
                        Image(
                            painter = painterResource(profileImage),
                            contentDescription = "Foto profil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Text(
                            student.inisial,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2B4BB8)
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {

                    Text(
                        "MAHASISWA AKTIF",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    )
                    Text(
                        student.nama,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "${student.prodi} • Angkatan ${student.angkatan}",
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Color.White
                    )
                    Text("NIM: ${student.nim}",
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Color.White
                    )
                }
            }

            val badgeTexts = listOf(
                "Joined Clubs" to "Komunitas yang Diikuti",
                "Total Projects" to "Seluruh Proyek Dibuat"
            )
            val badgeCounts = listOf(uiState.joinedCount, uiState.totalProjects)
            val badgeIcons = listOf(Icons.Filled.Groups, Icons.Filled.Layers)
            val badgeBg = listOf(Color(0xFFE8EEFF), Color(0xFFEDE9FE))
            val badgeTint = listOf(blue, Color(0xFF6D28D9))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                badgeTexts.forEachIndexed {i, (label, desc) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = border
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(badgeBg[i]),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    badgeIcons[i],
                                    contentDescription = null,
                                    tint = badgeTint[i],
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Text(
                                badgeCounts[i].toString(),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(label,
                                fontSize = 14.sp,
                                lineHeight = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(desc,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, contentDescription = null, tint = blue)
                    Text(
                        "Semua Komunitas yang Diikuti (${uiState.joinedCount})",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )

                    Text(
                        if (uiState.joinedCount > 0) "Status: Joined" else "Status: Not Joined",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (uiState.joinedCount > 0) blue else Color.Gray
                    )
                }

                if (uiState.joinedCommunities.isEmpty()) {
                    Text("Belum bergabung dengan komunitas", color = Color.Gray)
                } else {
                    uiState.joinedCommunities.forEach { community ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = border
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(community.color.copy(alpha = 0.12f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        community.icon,
                                        contentDescription = null,
                                        tint = community.color,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        community.nama,
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "${community.subtitle} • ${uiState.projectsIn(community.id).size} Proyek",
                                        fontSize = 12.sp,
                                        lineHeight = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                                Text(
                                    "Joined ✓",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFD32F2F),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(Color(0xFFFDECEC))
                                        .border(1.dp, Color(0xFFF5B5B5), RoundedCornerShape(50))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.CheckBoxOutlineBlank,
                        contentDescription = null,
                        tint = Color(0xFF6D28D9)
                    )
                    Text(
                        "Semua Proyek Mahasiswa (${uiState.totalProjects})",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                    Text(
                        "Total: ${uiState.totalProjects} Proyek",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (uiState.totalProjects > 0) blue else Color.Gray
                    )
                }

                if (uiState.visibleProjects.isEmpty()) {
                    Text("Belum ada proyek yang dicatat", color = Color.Gray)
                } else {
                    uiState.visibleProjects.forEach { project ->
                        val community = uiState.findCommunity(project.communityId)
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = border
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(project.color.copy(alpha = 0.12f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            project.icon,
                                            contentDescription = null,
                                            tint = project.color,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            community?.nama.orEmpty(),
                                            fontSize = 11.sp,
                                            lineHeight = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = community?.color ?: blue
                                        )
                                        Text(
                                            project.title,
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            project.tech.joinToString(" • "),
                                            fontSize = 12.sp,
                                            lineHeight = 16.sp,
                                            color = Color.Gray
                                        )
                                    }
                                }
                                HorizontalDivider(color = Color(0xFFE5E7EB))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        if (project.completed) "Completed" else "In Progress",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (project.completed) Color(0xFF15803D) else Color(0xFFB45309),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(50))
                                            .background(
                                                if (project.completed) Color(0xFFDCFCE7) else Color(0xFFFEF3C7)
                                            )
                                            .padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HomeScreenPreview() {
//    AssignmentWeek6Theme{
//        HomeScreen(viewModel = viewModel())
//    }
//
//}