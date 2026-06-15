package com.runacombo.game

import com.runacombo.domain.model.ComboType
import com.runacombo.domain.model.Rune
import com.runacombo.domain.model.RuneCombo
import com.runacombo.domain.model.RuneType
import kotlin.random.Random

class GameEngine(
    private val width: Int = 7,
    private val height: Int = 7
) {
    private var board: Array<IntArray> = generateInitialBoard()
    private var score: Int = 0
    private var moves: Int = 20
    private var combo: Int = 0

    private fun generateInitialBoard(): Array<IntArray> {
        return Array(height) { IntArray(width) { Random.nextInt(1, 7) } }
    }

    fun getBoard(): Array<IntArray> = board

    fun getScore(): Int = score

    fun getMoves(): Int = moves

    fun getCombo(): Int = combo

    /**
     * Проверяет, есть ли комбо на доске
     */
    fun findCombos(): List<RuneCombo> {
        val combos = mutableListOf<RuneCombo>()

        // Проверяем горизонтальные комбо
        for (y in 0 until height) {
            for (x in 0 until width - 2) {
                val runeId = board[y][x]
                if (board[y][x + 1] == runeId && board[y][x + 2] == runeId) {
                    val comboRunes = mutableListOf<Int>()
                    var endX = x
                    while (endX < width && board[y][endX] == runeId) {
                        comboRunes.add(runeId)
                        endX++
                    }

                    if (comboRunes.size >= 3) {
                        val damage = calculateDamage(comboRunes.size)
                        combo++
                        score += damage
                    }
                }
            }
        }

        // Проверяем вертикальные комбо
        for (x in 0 until width) {
            for (y in 0 until height - 2) {
                val runeId = board[y][x]
                if (board[y + 1][x] == runeId && board[y + 2][x] == runeId) {
                    val comboRunes = mutableListOf<Int>()
                    var endY = y
                    while (endY < height && board[endY][x] == runeId) {
                        comboRunes.add(runeId)
                        endY++
                    }

                    if (comboRunes.size >= 3) {
                        val damage = calculateDamage(comboRunes.size)
                        combo++
                        score += damage
                    }
                }
            }
        }

        return combos
    }

    /**
     * Удаляет комбо с доски
     */
    fun removeCombos(positions: List<Pair<Int, Int>>) {
        for ((x, y) in positions) {
            if (x in 0 until width && y in 0 until height) {
                board[y][x] = 0 // 0 = пусто
            }
        }
        applyGravity()
        fillEmpty()
    }

    /**
     * Применяет гравитацию (руны падают вниз)
     */
    private fun applyGravity() {
        for (x in 0 until width) {
            var writePos = height - 1
            for (readPos in height - 1 downTo 0) {
                if (board[readPos][x] != 0) {
                    board[writePos][x] = board[readPos][x]
                    if (writePos != readPos) {
                        board[readPos][x] = 0
                    }
                    writePos--
                }
            }
        }
    }

    /**
     * Заполняет пустые ячейки новыми рунами
     */
    private fun fillEmpty() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (board[y][x] == 0) {
                    board[y][x] = Random.nextInt(1, 7)
                }
            }
        }
    }

    /**
     * Вычисляет урон на основе размера комбо
     */
    private fun calculateDamage(comboSize: Int): Int {
        return when {
            comboSize >= 5 -> 250
            comboSize >= 4 -> 150
            comboSize >= 3 -> 100
            else -> 0
        }
    }

    /**
     * Проверяет, возможны ли ещё ходы
     */
    fun hasValidMoves(): Boolean {
        // Проверяем, есть ли возможные комбо
        for (y in 0 until height) {
            for (x in 0 until width) {
                // Проверяем горизонтальный обмен
                if (x < width - 1) {
                    swapRunes(x, y, x + 1, y)
                    if (findCombos().isNotEmpty()) {
                        swapRunes(x, y, x + 1, y) // Отменяем обмен
                        return true
                    }
                    swapRunes(x, y, x + 1, y) // Отменяем обмен
                }

                // Проверяем вертикальный обмен
                if (y < height - 1) {
                    swapRunes(x, y, x, y + 1)
                    if (findCombos().isNotEmpty()) {
                        swapRunes(x, y, x, y + 1) // Отменяем обмен
                        return true
                    }
                    swapRunes(x, y, x, y + 1) // Отменяем обмен
                }
            }
        }
        return false
    }

    /**
     * Обменивает две руны
     */
    fun swapRunes(x1: Int, y1: Int, x2: Int, y2: Int) {
        if (isValidPosition(x1, y1) && isValidPosition(x2, y2)) {
            val temp = board[y1][x1]
            board[y1][x1] = board[y2][x2]
            board[y2][x2] = temp
        }
    }

    /**
     * Проверяет, валидна ли позиция
     */
    private fun isValidPosition(x: Int, y: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }

    /**
     * Выполняет ход
     */
    fun makeMove(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        if (!isValidPosition(x1, y1) || !isValidPosition(x2, y2)) {
            return false
        }

        // Проверяем, что это соседние ячейки
        if (Math.abs(x1 - x2) + Math.abs(y1 - y2) != 1) {
            return false
        }

        swapRunes(x1, y1, x2, y2)

        if (findCombos().isEmpty()) {
            // Отменяем ход, если нет комбо
            swapRunes(x1, y1, x2, y2)
            return false
        }

        moves--
        return true
    }

    /**
     * Сбрасывает игру
     */
    fun reset() {
        board = generateInitialBoard()
        score = 0
        moves = 20
        combo = 0
    }

    /**
     * Проверяет, игра окончена
     */
    fun isGameOver(): Boolean {
        return moves <= 0 || !hasValidMoves()
    }

    /**
     * Проверяет, выиграна ли игра
     */
    fun isGameWon(): Boolean {
        return score >= 1000 // Целевой счёт
    }
}
