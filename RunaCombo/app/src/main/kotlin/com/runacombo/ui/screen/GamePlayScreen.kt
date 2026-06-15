package com.runacombo.ui.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runacombo.domain.model.RuneType
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.theme.*

@Composable
fun GamePlayScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val gameState by viewModel.gameState.collectAsState()

    if (gameState == null) {
        LaunchedEffect(Unit) {
            viewModel.initializeGameBoard()
        }
    }

    val state = gameState ?: return

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
            // Top Bar
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
                    shape = CircleShape
                ) {
                    Text("←")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Уровень 1",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                    Text(
                        text = "Ходов: ${state.moves}",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Очки: ${state.score}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                    Text(
                        text = "Комбо: ${state.combo}",
                        fontSize = 12.sp,
                        color = Accent
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Game Board
            GameBoard(
                boardState = state.boardState,
                selectedRunes = state.selectedRunes,
                onRuneClick = { x, y -> viewModel.selectRune(x, y) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Submit Button
            Button(
                onClick = { viewModel.submitMove() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Success,
                    contentColor = Background
                ),
                enabled = state.selectedRunes.size >= 3 && !state.isAnimating,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Подтвердить комбо (${state.selectedRunes.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (state.gameOver) {
                Spacer(modifier = Modifier.height(20.dp))
                GameOverDialog(
                    score = state.score,
                    won = state.won,
                    onRestart = { viewModel.initializeGameBoard() },
                    onBack = onBack
                )
            }
        }
    }
}

@Composable
fun GameBoard(
    boardState: Array<Array<Int>>,
    selectedRunes: List<Pair<Int, Int>>,
    onRuneClick: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(
                color = Surface.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (y in boardState.indices) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (x in boardState[y].indices) {
                    RuneCell(
                        runeId = boardState[y][x],
                        isSelected = selectedRunes.contains(Pair(x, y)),
                        onClick = { onRuneClick(x, y) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun RuneCell(
    runeId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rune_glow")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val runeType = when (runeId) {
        1 -> RuneType.FIRE
        2 -> RuneType.WATER
        3 -> RuneType.NATURE
        4 -> RuneType.LIGHTNING
        5 -> RuneType.LIGHT
        else -> RuneType.SHADOW
    }

    val runeColor = when (runeType) {
        RuneType.FIRE -> RuneFire
        RuneType.WATER -> RuneWater
        RuneType.NATURE -> RuneNature
        RuneType.LIGHTNING -> RuneLightning
        RuneType.LIGHT -> RuneLight
        RuneType.SHADOW -> RuneShadow
    }

    val runeEmoji = when (runeType) {
        RuneType.FIRE -> "🔥"
        RuneType.WATER -> "🌊"
        RuneType.NATURE -> "🌿"
        RuneType.LIGHTNING -> "⚡"
        RuneType.LIGHT -> "✨"
        RuneType.SHADOW -> "🌑"
    }

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .scale(if (isSelected) scale else 1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = runeColor.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = if (isSelected) {
                        Brush.radialGradient(
                            colors = listOf(
                                runeColor.copy(alpha = 1f),
                                runeColor.copy(alpha = 0.6f)
                            )
                        )
                    } else {
                        Brush.radialGradient(
                            colors = listOf(
                                runeColor.copy(alpha = 0.8f),
                                runeColor.copy(alpha = 0.4f)
                            )
                        )
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = runeEmoji,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Accent.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }
        }
    }
}

@Composable
fun GameOverDialog(
    score: Int,
    won: Boolean,
    onRestart: () -> Unit,
    onBack: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (won) "🎉 ПОБЕДА!" else "💔 ПОРАЖЕНИЕ",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = if (won) Success else Error
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ваш результат: $score очков",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Error
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Назад")
                }

                Button(
                    onClick = onRestart,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Success
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Заново")
                }
            }
        }
    }
}
