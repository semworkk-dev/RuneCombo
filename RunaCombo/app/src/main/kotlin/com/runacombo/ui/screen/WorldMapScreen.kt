package com.runacombo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.theme.*

@Composable
fun WorldMapScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val locations = listOf(
        LocationInfo("🏠 Деревня Домового", "Начальная локация", 10, 0, true),
        LocationInfo("🦊 Лес Лисы", "Таинственный лес", 10, 5, true),
        LocationInfo("⚔️ Крепость Богатыря", "Мощная крепость", 10, 0, false),
        LocationInfo("🎪 Ярмарочная площадь", "Оживленная площадь", 10, 0, false),
        LocationInfo("🌲 Заколдованные болота", "Опасные болота", 10, 0, false),
        LocationInfo("💎 Горы Самоцветов", "Сверкающие горы", 10, 0, false),
        LocationInfo("❄️ Ледяной Терем", "Ледяной дворец", 10, 0, false),
        LocationInfo("☠️ Башня Кощея", "Финальная локация", 20, 0, false)
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
                    text = "🗺️ КАРТА МИРА",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(40.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Locations List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(locations) { location ->
                    LocationCard(location = location)
                }
            }
        }
    }
}

data class LocationInfo(
    val name: String,
    val description: String,
    val totalLevels: Int,
    val completedLevels: Int,
    val isUnlocked: Boolean
)

@Composable
fun LocationCard(location: LocationInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = location.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (location.isUnlocked) Primary else TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = location.description,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = location.completedLevels.toFloat() / location.totalLevels.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = Success,
                    trackColor = Surface
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = "${location.completedLevels}/${location.totalLevels}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (location.isUnlocked) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .height(32.dp)
                            .width(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Играть",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text(
                        text = "🔒",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
