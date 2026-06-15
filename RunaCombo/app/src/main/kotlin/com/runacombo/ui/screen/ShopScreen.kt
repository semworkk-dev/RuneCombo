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
fun ShopScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val shopItems = listOf(
        ShopItem("💎 100 кристаллов", "99 руб.", Primary),
        ShopItem("💎 500 кристаллов", "399 руб.", Primary),
        ShopItem("💎 1000 кристаллов", "699 руб.", Primary),
        ShopItem("💰 10000 монет", "49 руб.", Success),
        ShopItem("🎁 Сундук героя", "199 руб.", Accent),
        ShopItem("✨ Премиум пакет", "999 руб.", Primary)
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
                    text = "🛍️ МАГАЗИН",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(40.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Shop Items List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(shopItems) { item ->
                    ShopItemCard(item = item)
                }
            }
        }
    }
}

data class ShopItem(
    val name: String,
    val price: String,
    val color: androidx.compose.ui.graphics.Color
)

@Composable
fun ShopItemCard(item: ShopItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
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
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Специальное предложение",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = item.color
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(32.dp)
                        .width(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = item.color
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Купить",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
