package com.darien.week6vp.Data


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.ui.graphics.Color
import com.darien.week6vp.Model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CommunityRepository {

    private val _communities = MutableStateFlow(
        listOf(
            Community(
                id = "googledev",
                nama = "GDSC",
                subtitle = "Google Developer Student Club",
                description = "Fokus pada ekosistem Google: Android Compose, Flutter, Firebase, dan Google Cloud Platform.",
                MemberTotal = 142,
                icon = Icons.Filled.Code,
                color = Color(0xFF2F5BEA)
            ),
            Community(
                id = "cybersecurity",
                nama = "CyberSecurity Guild",
                subtitle = "Security & Pentesting",
                description = "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.",
                MemberTotal = 88,
                icon = Icons.Filled.Shield,
                color = Color(0xFFD32F2F)
            ),
            Community(
                id = "ai",
                nama = "AI Society",
                subtitle = "Machine Learning & LLM",
                description = "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.",
                MemberTotal = 115,
                icon = Icons.Filled.AutoAwesome,
                color = Color(0xFF7C3AED)
            ),
            Community(
                id = "mobiledev",
                nama = "Mobile Dev Community",
                subtitle = "Native Jetpack Compose & IOS",
                description = "Pengembangan Aplikasi Mobile Menggunakan Kotlin dan Native Jetpack Compose.",
                MemberTotal = 95,
                icon = Icons.Filled.PhoneAndroid,
                color = Color(0xFF0288D1)
            ),
            Community(
                id = "mieup",
                nama = "Group Mie UP",
                subtitle = "Mie UP Cak Ipin",
                description = "Mukbang Mie UP bersama Kenjo dan Sean Lawton",
                MemberTotal = 3,
                icon = Icons.Filled.Fastfood,
                color = Color(0xFF059669)
            ),
            Community(
                id = "motor",
                nama = "Biker Club",
                subtitle = "Ride Or Die",
                description = "Wheelie-wheelie depan kampus sambil geber geber",
                MemberTotal = 20,
                icon = Icons.Filled.SportsMotorsports,
                color = Color(0xFFEA580C)
            )
        )
    )
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()

    fun toggleJoin(communityId: String) {
        _communities.update { list ->
            list.map { if (it.id == communityId) it.copy(joined = !it.joined) else it }
        }
    }
}