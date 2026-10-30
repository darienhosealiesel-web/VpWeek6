package com.darien.week6vp.ui.Screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darien.week6vp.ViewModel.ProjekUiState
import kotlinx.coroutines.flow.Flow

@Composable
fun CommunityScreen(
    uiState: ProjekUiState,
    snackbarEvents: Flow<String>,
    onToggleJoin: (communityId: String) -> Unit,
    onCommunityClick: (communityId: String) -> Unit
) {
    val listState = rememberLazyListState()
    val snackbarHostState = remember {SnackbarHostState()}

    val blue = Color(0xFF2F5BEA)
    val red = Color(0xFFD32F2F)
    val border = BorderStroke(1.dp, Color(0xFFE5E7EB))

    LaunchedEffect(snackbarEvents) {
        snackbarEvents.collect { message ->
            snackbarHostState.showSnackbar(message = message, actionLabel = "OK")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FC))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Communities Directory", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(
                        "Pilih komunitas untuk membuka Club Projects",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                Icon(Icons.Filled.Search, contentDescription = "Cari")
            }
            HorizontalDivider(color = Color(0xFFE5E7EB))

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(uiState.communities, key = {it.id}) {community ->
                    Card(
                        onClick = { onCommunityClick(community.id) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = border
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(community.color.copy(alpha = 0.12f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        community.icon,
                                        contentDescription = null,
                                        tint = community.color,
                                        modifier = Modifier.size(26.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        community.nama,
                                        fontSize = 18.sp,
                                        lineHeight = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "${community.subtitle} • ${community.MemberTotal} Mahasiswa",
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Text(
                                community.description,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = Color(0xFF374151)
                            )

                            HorizontalDivider(color = Color(0xFFE5E7EB))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (community.joined) {
                                    Icon(
                                        Icons.Filled.LockOpen,
                                        contentDescription = null,
                                        tint = blue,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        "Unlocked • Buka Proyek →",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = blue,
                                        modifier = Modifier.weight(1f)
                                    )
                                    OutlinedButton(
                                        onClick = {onToggleJoin(community.id)},
                                        shape = RoundedCornerShape(50),
                                        border = BorderStroke(1.dp, red),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            containerColor = Color.White,
                                            contentColor = red
                                        )
                                    ) {
                                        Icon(
                                            Icons.Filled.Check,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Text("Joined", fontWeight = FontWeight.Bold)
                                    }
                                } else {
                                    Icon(
                                        Icons.Filled.Lock,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        "Terkunci (Gate Akses)",
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Button(
                                        onClick = {onToggleJoin(community.id)},
                                        shape = RoundedCornerShape(50),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = blue,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("+  Join", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) { data ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1C1E))
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF9500)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "!",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset(y = (-1).dp)
                    )
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = data.visuals.message,
                    color = Color.White,
                    fontSize = 13.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.widthIn(max = 260.dp)
                )
                Spacer(Modifier.width(8.dp))
                TextButton(onClick = { data.dismiss() }) {
                    Text(
                        text = "OK",
                        color = Color(0xFF8AB4F8),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CommunitiesScreenPreview() {
//    AssignmentWeek6Theme {
//        CommunitiesScreen(viewModel = viewModel(), onOpenProjects = {})
//    }
//}