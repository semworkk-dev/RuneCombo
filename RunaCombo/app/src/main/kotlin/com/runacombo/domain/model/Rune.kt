package com.runacombo.domain.model

data class Rune(
    val id: Int,
    val name: String,
    val type: RuneType,
    val rarity: Int,
    val damage: Int,
    val effect: String,
    val color: String
)

enum class RuneType(val displayName: String) {
    FIRE("Огонь"),
    WATER("Вода"),
    NATURE("Природа"),
    LIGHTNING("Молния"),
    LIGHT("Свет"),
    SHADOW("Тень")
}

data class RuneCombo(
    val runes: List<Rune>,
    val comboType: ComboType,
    val multiplier: Float,
    val damage: Int
)

enum class ComboType {
    MATCH_3,
    MATCH_4,
    MATCH_5,
    CHAIN,
    SPECIAL
}
