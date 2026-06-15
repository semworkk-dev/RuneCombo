package com.runacombo.utils

object Constants {
    // Game Constants
    const val BOARD_WIDTH = 7
    const val BOARD_HEIGHT = 7
    const val INITIAL_MOVES = 20
    const val INITIAL_ENERGY = 30
    const val MAX_ENERGY = 30
    const val ENERGY_REFILL_TIME_MINUTES = 5
    const val TARGET_SCORE = 1000

    // API Constants
    const val API_BASE_URL = "https://api.runacombo.com/"
    const val API_TIMEOUT_SECONDS = 30L
    const val SYNC_INTERVAL_MINUTES = 5

    // Database Constants
    const val DATABASE_NAME = "runa_combo_db"

    // Hero Constants
    const val TOTAL_HEROES = 6
    const val INITIAL_UNLOCKED_HEROES = 2 // Богатырь и Домовой

    // Rune Constants
    const val TOTAL_RUNES = 6
    const val INITIAL_UNLOCKED_RUNES = 6

    // Location Constants
    const val TOTAL_LOCATIONS = 8
    const val INITIAL_UNLOCKED_LOCATIONS = 1

    // Level Constants
    const val LEVELS_PER_LOCATION = 10
    const val BOSS_LEVEL_INTERVAL = 10

    // Rarity Constants
    const val RARITY_COMMON = 1
    const val RARITY_RARE = 2
    const val RARITY_EPIC = 3
    const val RARITY_LEGENDARY = 4
    const val RARITY_ANCIENT = 5

    // Animation Constants
    const val ANIMATION_DURATION_MS = 300
    const val COMBO_ANIMATION_DURATION_MS = 500
    const val SPLASH_SCREEN_DURATION_MS = 3000

    // Shared Preferences Keys
    const val PREF_PLAYER_ID = "player_id"
    const val PREF_LAST_SYNC = "last_sync"
    const val PREF_FIRST_LAUNCH = "first_launch"

    // Error Messages
    const val ERROR_NETWORK = "Ошибка сети"
    const val ERROR_DATABASE = "Ошибка базы данных"
    const val ERROR_INVALID_MOVE = "Недопустимый ход"
    const val ERROR_INSUFFICIENT_ENERGY = "Недостаточно энергии"

    // Success Messages
    const val SUCCESS_LEVEL_COMPLETED = "Уровень пройден!"
    const val SUCCESS_HERO_UNLOCKED = "Герой разблокирован!"
    const val SUCCESS_RUNE_UNLOCKED = "Руна разблокирована!"
}
