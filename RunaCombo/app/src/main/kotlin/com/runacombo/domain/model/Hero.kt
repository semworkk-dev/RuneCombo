package com.runacombo.domain.model

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val rarity: Int,
    val level: Int,
    val experience: Long,
    val health: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val ability: HeroAbility,
    val isUnlocked: Boolean,
    val equippedRunes: List<Int>
)

data class HeroAbility(
    val name: String,
    val description: String,
    val cooldown: Int,
    val damage: Int,
    val effect: String
)

// Predefined heroes
object HeroPresets {
    val BOGATYR = Hero(
        id = 1,
        name = "Богатырь",
        description = "Могучий воин с огромной силой",
        rarity = 5,
        level = 1,
        experience = 0,
        health = 150,
        attack = 120,
        defense = 80,
        speed = 60,
        ability = HeroAbility(
            name = "Огненный удар",
            description = "Наносит огненный удар",
            cooldown = 3,
            damage = 200,
            effect = "FIRE"
        ),
        isUnlocked = true,
        equippedRunes = listOf()
    )

    val DOMOVOY = Hero(
        id = 2,
        name = "Домовой",
        description = "Хранитель дома, дарует удачу",
        rarity = 4,
        level = 1,
        experience = 0,
        health = 100,
        attack = 70,
        defense = 90,
        speed = 75,
        ability = HeroAbility(
            name = "Дополнительный ход",
            description = "Дает дополнительный ход",
            cooldown = 2,
            damage = 0,
            effect = "EXTRA_TURN"
        ),
        isUnlocked = true,
        equippedRunes = listOf()
    )

    val LISA = Hero(
        id = 3,
        name = "Лиса",
        description = "Хитрая лиса, мастер ловушек",
        rarity = 4,
        level = 1,
        experience = 0,
        health = 90,
        attack = 100,
        defense = 60,
        speed = 110,
        ability = HeroAbility(
            name = "Удаляет случайные руны",
            description = "Удаляет случайные руны с поля",
            cooldown = 3,
            damage = 0,
            effect = "REMOVE_RUNES"
        ),
        isUnlocked = false,
        equippedRunes = listOf()
    )

    val ALISA = Hero(
        id = 4,
        name = "Алиса",
        description = "Волшебница с магическими способностями",
        rarity = 5,
        level = 1,
        experience = 0,
        health = 110,
        attack = 140,
        defense = 70,
        speed = 85,
        ability = HeroAbility(
            name = "Взрыв магии",
            description = "Магический взрыв наносит урон",
            cooldown = 3,
            damage = 250,
            effect = "MAGIC_BURST"
        ),
        isUnlocked = false,
        equippedRunes = listOf()
    )

    val KOLOBOK = Hero(
        id = 5,
        name = "Колобок",
        description = "Веселый колобок, приносит удачу",
        rarity = 3,
        level = 1,
        experience = 0,
        health = 80,
        attack = 60,
        defense = 50,
        speed = 95,
        ability = HeroAbility(
            name = "Удача и бонусы",
            description = "Увеличивает шанс получить бонусы",
            cooldown = 2,
            damage = 0,
            effect = "LUCK_BOOST"
        ),
        isUnlocked = false,
        equippedRunes = listOf()
    )

    val KOT_BAYUN = Hero(
        id = 6,
        name = "Кот Баюн",
        description = "Волшебный кот, усыпляет врагов",
        rarity = 4,
        level = 1,
        experience = 0,
        health = 95,
        attack = 85,
        defense = 75,
        speed = 80,
        ability = HeroAbility(
            name = "Усыпляет врагов",
            description = "Усыпляет врагов на несколько ходов",
            cooldown = 4,
            damage = 0,
            effect = "SLEEP"
        ),
        isUnlocked = false,
        equippedRunes = listOf()
    )

    val ALL_HEROES = listOf(BOGATYR, DOMOVOY, LISA, ALISA, KOLOBOK, KOT_BAYUN)
}
