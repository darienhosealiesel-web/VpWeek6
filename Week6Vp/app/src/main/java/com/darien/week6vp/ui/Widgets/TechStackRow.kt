package com.darien.week6vp.ui.Widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TechStackRow(
    items: List<String>,
    modifier: Modifier = Modifier,
    selected: List<String> = emptyList(),
    onToggle: ((String) -> Unit)? = null
) {
    val blue = Color(0xFF2F5BEA)

    Layout(
        modifier = modifier,
        content = {
            items.forEach { tech ->
                if (onToggle != null) {
                    val isSelected = tech in selected
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Color(0xFFE8EEFF) else Color.White)
                            .border(
                                1.dp,
                                if (isSelected) blue else Color(0xFFD1D5DB),
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { onToggle(tech) }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = if (isSelected) "✓ $tech" else "+ $tech",
                            fontSize = 13.sp,
                            lineHeight = 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) blue else Color(0xFF6B7280),
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF3F4F6))
                            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tech,
                            fontSize = 11.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF4B5563),
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
        }
    ) { measurables, constraints ->
        val gap = 8.dp.roundToPx()
        val placeables = measurables.map {
            it.measure(constraints.copy(minWidth = 0, minHeight = 0))
        }

        val positions = ArrayList<Pair<Int, Int>>(placeables.size)
        var x = 0
        var y = 0
        var rowHeight = 0
        var widest = 0
        placeables.forEach { p ->
            if (x > 0 && x + p.width > constraints.maxWidth) {
                x = 0
                y += rowHeight + gap
                rowHeight = 0
            }
            positions.add(x to y)
            x += p.width
            widest = maxOf(widest, x)
            x += gap
            rowHeight = maxOf(rowHeight, p.height)
        }

        val width = if (constraints.hasBoundedWidth) constraints.maxWidth else widest
        val height = if (placeables.isEmpty()) 0 else y + rowHeight

        layout(width, height) {
            placeables.forEachIndexed { i, p ->
                p.placeRelative(positions[i].first, positions[i].second)
            }
        }
    }
}