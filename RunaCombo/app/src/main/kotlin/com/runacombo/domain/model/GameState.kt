package com.runacombo.domain.model

data class GameState(
    val boardState: Array<Array<Int>>, // Rune IDs on board
    val score: Int = 0,
    val moves: Int = 0,
    val combo: Int = 0,
    val selectedRunes: List<Pair<Int, Int>> = emptyList(), // Coordinates of selected runes
    val isAnimating: Boolean = false,
    val gameOver: Boolean = false,
    val won: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (!boardState.contentDeepEquals(other.boardState)) return false
        if (score != other.score) return false
        if (moves != other.moves) return false
        if (combo != other.combo) return false
        if (selectedRunes != other.selectedRunes) return false
        if (isAnimating != other.isAnimating) return false
        if (gameOver != other.gameOver) return false
        if (won != other.won) return false

        return true
    }

    override fun hashCode(): Int {
        var result = boardState.contentDeepHashCode()
        result = 31 * result + score
        result = 31 * result + moves
        result = 31 * result + combo
        result = 31 * result + selectedRunes.hashCode()
        result = 31 * result + isAnimating.hashCode()
        result = 31 * result + gameOver.hashCode()
        result = 31 * result + won.hashCode()
        return result
    }
}

data class BattleState(
    val playerHealth: Int,
    val enemyHealth: Int,
    val playerMaxHealth: Int,
    val enemyMaxHealth: Int,
    val playerHeroes: List<Hero>,
    val currentEnemy: Enemy,
    val round: Int = 1,
    val playerTurn: Boolean = true,
    val battleLog: List<String> = emptyList()
)

data class Enemy(
    val id: Int,
    val name: String,
    val health: Int,
    val maxHealth: Int,
    val attack: Int,
    val defense: Int,
    val abilities: List<String>
)
