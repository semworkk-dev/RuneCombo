package com.runacombo.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runacombo.ui.theme.*

/**
 * Кнопка в стиле РунаКомбо
 */
@Composable
fun RunaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Primary,
    textColor: Color = Background
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = Surface,
            disabledContentColor = TextSecondary
        ),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Карточка в стиле РунаКомбо
 */
@Composable
fun RunaCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Surface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        content()
    }
}

/**
 * Загрузочный индикатор
 */
@Composable
fun RunaLoadingIndicator(
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 40.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .size(size)
            .rotate(rotation)
            .background(
                brush = Brush.sweepGradient(
                    colors = listOf(Accent, Primary, Accent),
                    center = androidx.compose.ui.geometry.Offset(size.value / 2, size.value / 2)
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size * 0.75f)
                .background(
                    color = Background,
                    shape = CircleShape
                )
        )
    }
}

/**
 * Диалог подтверждения
 */
@Composable
fun RunaConfirmDialog(
    title: String,
    message: String,
    confirmText: String = "Да",
    cancelText: String = "Нет",
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
        },
        text = {
            Text(
                text = message,
                fontSize = 14.sp,
                color = TextPrimary
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Success
                )
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Error
                )
            ) {
                Text(cancelText)
            }
        },
        containerColor = Surface,
        textContentColor = TextPrimary,
        titleContentColor = Primary
    )
}

/**
 * Прогресс бар в стиле РунаКомбо
 */
@Composable
fun RunaProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Surface,
    progressColor: Color = Primary
) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .height(8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            ),
        color = progressColor,
        trackColor = backgroundColor
    )
}

/**
 * Информационный баннер
 */
@Composable
fun RunaBanner(
    message: String,
    type: BannerType = BannerType.INFO,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (type) {
        BannerType.SUCCESS -> Success.copy(alpha = 0.2f)
        BannerType.ERROR -> Error.copy(alpha = 0.2f)
        BannerType.WARNING -> Primary.copy(alpha = 0.2f)
        BannerType.INFO -> Accent.copy(alpha = 0.2f)
    }

    val textColor = when (type) {
        BannerType.SUCCESS -> Success
        BannerType.ERROR -> Error
        BannerType.WARNING -> Primary
        BannerType.INFO -> Accent
    }

    val icon = when (type) {
        BannerType.SUCCESS -> "✓"
        BannerType.ERROR -> "✕"
        BannerType.WARNING -> "!"
        BannerType.INFO -> "ℹ"
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = message,
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

enum class BannerType {
    SUCCESS, ERROR, WARNING, INFO
}

/**
 * Пустой экран
 */
@Composable
fun EmptyState(
    icon: String,
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            fontSize = 64.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Разделитель
 */
@Composable
fun RunaDivider(
    modifier: Modifier = Modifier,
    color: Color = Surface
) {
    Divider(
        modifier = modifier.height(1.dp),
        color = color
    )
}
