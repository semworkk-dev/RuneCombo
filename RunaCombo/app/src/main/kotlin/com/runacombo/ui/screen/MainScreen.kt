package com.runacombo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.theme.*

@Composable
fun MainScreen(
    viewModel: GameViewModel,
    onPlayClick: () -> Unit,
    onHeroesClick: () -> Unit,
    onRunesClick: () -> Unit,
    onShopClick: () -> Unit
) {
    val playerState by viewModel.playerState.collectAsState()

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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Resources Bar
            ResourceBar(playerState)

            Spacer(modifier = Modifier.height(32.dp))

            // Game Title
            Text(
                text = "РУНАКОМБО",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Создавай комбо. Пробуждай руны. Побеждай легенд.",
                fontSize = 14.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Play Button
            Button(
                onClick = onPlayClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Success,
                    contentColor = Background
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "🟢 ИГРАТЬ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Menu Grid
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuButton(
                    icon = "🏆",
                    label = "Герои",
                    onClick = onHeroesClick,
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = "✨",
                    label = "Руны",
                    onClick = onRunesClick,
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = "🛍️",
                    label = "Магазин",
                    onClick = onShopClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuButton(
                    icon = "📅",
                    label = "Награды",
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = "📋",
                    label = "Задания",
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = "🎉",
                    label = "События",
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Game Description
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Surface.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Добро пожаловать в РунаКомбо!\n\nСоединяй магические руны, создавай мощные комбо и сражайся с легендарными врагами. Разблокируй героев, собери коллекцию рун и покори все локации славянского мира!",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp,
                    color = TextPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun ResourceBar(playerState: com.runacombo.data.db.entity.PlayerEntity?) {
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
        ResourceItem(icon = "💰", label = "Монеты", value = playerState?.coins?.toString() ?: "0")
        ResourceItem(icon = "💎", label = "Кристаллы", value = playerState?.crystals?.toString() ?: "0")
        ResourceItem(icon = "⚡", label = "Энергия", value = "${playerState?.energy ?: 0}/${playerState?.maxEnergy ?: 30}")
    }
}

@Composable
fun ResourceItem(icon: String, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = icon, fontSize = 24.sp)
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Primary
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = TextSecondary
        )
    }
}

@Composable
fun MenuButton(
    icon: String,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Surface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = icon, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}
