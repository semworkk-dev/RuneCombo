package com.runacombo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runacombo.domain.model.HeroPresets
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.theme.*

@Composable
fun HeroesScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val heroes = HeroPresets.ALL_HEROES

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
                    text = "🏆 КОЛЛЕКЦИЯ ГЕРОЕВ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(40.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Heroes Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(heroes) { hero ->
                    HeroCard(hero = hero)
                }
            }
        }
    }
}

@Composable
fun HeroCard(hero: com.runacombo.domain.model.Hero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Accent.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (hero.id) {
                        1 -> "⚔️"
                        2 -> "🏠"
                        3 -> "🦊"
                        4 -> "✨"
                        5 -> "🟡"
                        else -> "🐈"
                    },
                    fontSize = 32.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Hero Name
            Text(
                text = hero.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Primary,
                textAlign = TextAlign.Center
            )

            // Rarity Stars
            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(hero.rarity) {
                    Text("⭐", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Stats
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Background.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem("❤️", hero.health.toString())
                StatItem("⚔️", hero.attack.toString())
                StatItem("🛡️", hero.defense.toString())
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Ability
            Text(
                text = hero.ability.name,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Accent,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            if (!hero.isUnlocked) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "🔒 Заблокирован",
                    fontSize = 10.sp,
                    color = Error
                )
            }
        }
    }
}

@Composable
fun StatItem(icon: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(icon, fontSize = 12.sp)
        Text(
            text = value,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}
