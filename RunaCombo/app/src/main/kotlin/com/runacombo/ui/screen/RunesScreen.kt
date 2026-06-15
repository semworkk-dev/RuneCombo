package com.runacombo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runacombo.domain.model.RuneType
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.theme.*

@Composable
fun RunesScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val runes = listOf(
        Triple("Огненная руна", RuneType.FIRE, 1),
        Triple("Ледяная руна", RuneType.WATER, 1),
        Triple("Руна природы", RuneType.NATURE, 1),
        Triple("Руна молнии", RuneType.LIGHTNING, 1),
        Triple("Руна света", RuneType.LIGHT, 2),
        Triple("Руна тени", RuneType.SHADOW, 2)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(GradientStart, GradientEnd)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Surface.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier.size(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Error
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("←")
                }

                Text(
                    text = "✨ КОЛЛЕКЦИЯ РУН",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(40.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Runes Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(runes) { (name, type, rarity) ->
                    RuneCardItem(name = name, type = type, rarity = rarity)
                }
            }
        }
    }
}

@Composable
fun RuneCardItem(name: String, type: RuneType, rarity: Int) {
    val runeColor = when (type) {
        RuneType.FIRE -> RuneFire
        RuneType.WATER -> RuneWater
        RuneType.NATURE -> RuneNature
        RuneType.LIGHTNING -> RuneLightning
        RuneType.LIGHT -> RuneLight
        RuneType.SHADOW -> RuneShadow
    }

    val runeEmoji = when (type) {
        RuneType.FIRE -> "🔥"
        RuneType.WATER -> "🌊"
        RuneType.NATURE -> "🌿"
        RuneType.LIGHTNING -> "⚡"
        RuneType.LIGHT -> "✨"
        RuneType.SHADOW -> "🌑"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(
            containerColor = runeColor.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            runeColor.copy(alpha = 0.8f),
                            runeColor.copy(alpha = 0.4f)
                        )
                    )
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = runeEmoji,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = name,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                repeat(rarity) {
                    Text("⭐", fontSize = 8.sp)
                }
            }
        }
    }
}
