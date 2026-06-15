package com.runacombo.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

/**
 * Расширение для форматирования времени
 */
fun Long.formatTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("ru", "RU"))
    return format.format(date)
}

/**
 * Расширение для форматирования больших чисел
 */
fun Long.formatNumber(): String {
    return when {
        this >= 1_000_000 -> String.format("%.1fM", this / 1_000_000.0)
        this >= 1_000 -> String.format("%.1fK", this / 1_000.0)
        else -> this.toString()
    }
}

/**
 * Расширение для форматирования больших чисел (Int)
 */
fun Int.formatNumber(): String {
    return this.toLong().formatNumber()
}

/**
 * Расширение для конвертации HEX в Color
 */
fun String.toComposeColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}

/**
 * Расширение для получения процента
 */
fun Int.getPercentOf(total: Int): Float {
    return if (total == 0) 0f else (this.toFloat() / total.toFloat())
}

/**
 * Расширение для проверки четности
 */
fun Int.isEven(): Boolean = this % 2 == 0

/**
 * Расширение для проверки нечетности
 */
fun Int.isOdd(): Boolean = this % 2 != 0

/**
 * Расширение для получения случайного элемента
 */
fun <T> List<T>.random(): T {
    return this[kotlin.random.Random.nextInt(this.size)]
}

/**
 * Расширение для безопасного получения элемента
 */
fun <T> List<T>.getOrNull(index: Int): T? {
    return if (index in 0 until this.size) this[index] else null
}

/**
 * Расширение для повтора действия
 */
inline fun <T> repeat(times: Int, action: (Int) -> T): List<T> {
    val result = mutableListOf<T>()
    repeat(times) { i ->
        result.add(action(i))
    }
    return result
}

/**
 * Расширение для проверки, находится ли число в диапазоне
 */
fun Int.inRange(min: Int, max: Int): Boolean {
    return this in min..max
}

/**
 * Расширение для ограничения числа диапазоном
 */
fun Int.clamp(min: Int, max: Int): Int {
    return when {
        this < min -> min
        this > max -> max
        else -> this
    }
}

/**
 * Расширение для конвертации миллисекунд в секунды
 */
fun Long.toSeconds(): Long = this / 1000

/**
 * Расширение для конвертации секунд в миллисекунды
 */
fun Long.toMillis(): Long = this * 1000

/**
 * Расширение для проверки, прошло ли определенное время
 */
fun Long.hasElapsed(milliseconds: Long): Boolean {
    return System.currentTimeMillis() - this >= milliseconds
}
