# Архитектура РунаКомбо

## 🏗️ Общая Архитектура

РунаКомбо использует **Clean Architecture** с **MVVM** паттерном для разделения ответственности и облегчения тестирования.

```
┌─────────────────────────────────────┐
│         Presentation Layer          │
│  (UI, Screens, ViewModels)          │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Domain Layer                │
│  (Business Logic, Models)           │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Data Layer                  │
│  (Repository, API, Database)        │
└─────────────────────────────────────┘
```

## 📁 Структура Папок

```
app/src/main/kotlin/com/runacombo/
├── data/                    # Data Layer
│   ├── api/                # REST API интеграция
│   │   └── ApiService.kt
│   ├── db/                 # Room Database
│   │   ├── RunaComboDatabase.kt
│   │   ├── Converters.kt
│   │   ├── entity/         # Database entities
│   │   └── dao/            # Data Access Objects
│   └── repository/         # Repository pattern
│       └── GameRepository.kt
├── domain/                 # Domain Layer
│   └── model/             # Domain models
│       ├── Rune.kt
│       ├── Hero.kt
│       └── GameState.kt
├── presentation/          # Presentation Layer
│   └── viewmodel/        # ViewModels
│       └── GameViewModel.kt
├── ui/                    # UI Layer
│   ├── screen/           # Compose screens
│   │   ├── SplashScreen.kt
│   │   ├── MainScreen.kt
│   │   ├── GamePlayScreen.kt
│   │   ├── HeroesScreen.kt
│   │   ├── RunesScreen.kt
│   │   ├── ShopScreen.kt
│   │   └── WorldMapScreen.kt
│   └── theme/            # Compose theme
│       ├── Color.kt
│       ├── Type.kt
│       └── Theme.kt
├── di/                   # Dependency Injection
│   └── AppModule.kt
├── game/                 # Game Logic
├── utils/                # Utilities
└── MainActivity.kt       # Entry point
```

## 🔄 Data Flow

### Поток Данных (Unidirectional)

```
User Action (UI)
    ↓
ViewModel (Process)
    ↓
Repository (Get Data)
    ↓
Database/API (Fetch)
    ↓
Repository (Transform)
    ↓
ViewModel (Update State)
    ↓
UI (Recompose)
```

### Пример: Загрузка Героев

```kotlin
// 1. UI вызывает ViewModel
viewModel.loadHeroes()

// 2. ViewModel вызывает Repository
repository.getUnlockedHeroes()

// 3. Repository получает данные из Database
database.heroDao().getUnlockedHeroes()

// 4. ViewModel обновляет State
_heroesState.value = heroes

// 5. UI реагирует на изменение State
val heroes by viewModel.heroesState.collectAsState()
```

## 🎯 Слои Архитектуры

### 1. Presentation Layer (UI)

**Ответственность:**
- Отображение данных пользователю
- Обработка пользовательского ввода
- Управление навигацией

**Компоненты:**
- `MainActivity.kt` - точка входа
- `Screen` composables - UI экраны
- `GameViewModel` - управление состоянием

**Технологии:**
- Jetpack Compose
- Navigation Compose
- Hilt ViewModel

### 2. Domain Layer

**Ответственность:**
- Бизнес-логика
- Определение моделей данных
- Правила игры

**Компоненты:**
- `Hero.kt` - модель героя
- `Rune.kt` - модель руны
- `GameState.kt` - состояние игры

**Особенности:**
- Независим от других слоев
- Легко тестируется
- Переиспользуется в разных проектах

### 3. Data Layer

**Ответственность:**
- Получение данных
- Кэширование
- Синхронизация

**Компоненты:**
- `GameRepository.kt` - главный репозиторий
- `ApiService.kt` - REST API клиент
- `RunaComboDatabase.kt` - локальная БД
- `DAO` интерфейсы - доступ к данным

**Источники Данных:**
- Local: Room Database
- Remote: Retrofit API

## 🔌 Dependency Injection

Используется **Hilt** для управления зависимостями.

### AppModule.kt

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideDatabase(): RunaComboDatabase { }
    
    @Singleton
    @Provides
    fun provideApiService(): ApiService { }
    
    @Singleton
    @Provides
    fun provideRepository(): GameRepository { }
}
```

### Использование в ViewModel

```kotlin
@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel()
```

## 💾 Управление Состоянием

### StateFlow для Реактивности

```kotlin
private val _playerState = MutableStateFlow<PlayerEntity?>(null)
val playerState: StateFlow<PlayerEntity?> = _playerState.asStateFlow()

// Использование в UI
val player by viewModel.playerState.collectAsState()
```

### Обновление Состояния

```kotlin
viewModelScope.launch {
    val player = repository.getPlayer("player_1")
    _playerState.value = player
}
```

## 🗄️ База Данных

### Room Database Schema

```
┌─────────────────┐
│     players     │
├─────────────────┤
│ id (PK)         │
│ nickname        │
│ level           │
│ coins           │
│ crystals        │
│ energy          │
└─────────────────┘

┌─────────────────┐
│     heroes      │
├─────────────────┤
│ id (PK)         │
│ name            │
│ level           │
│ isUnlocked      │
│ equippedRunes   │
└─────────────────┘

┌─────────────────┐
│     runes       │
├─────────────────┤
│ id (PK)         │
│ name            │
│ type            │
│ rarity          │
│ isUnlocked      │
└─────────────────┘
```

### Операции с БД

```kotlin
// Чтение
val hero = heroDao.getHero(heroId)

// Запись
heroDao.insertHero(hero)

// Обновление
heroDao.updateHero(hero)

// Удаление
heroDao.deleteHero(hero)

// Поток данных
val heroesFlow = heroDao.getAllHeroesFlow()
```

## 🌐 API Интеграция

### Retrofit Service

```kotlin
interface ApiService {
    @GET("api/v1/player/{playerId}")
    suspend fun getPlayer(@Path("playerId") playerId: String): PlayerResponse
    
    @POST("api/v1/player/sync")
    suspend fun syncPlayer(@Body playerData: PlayerSyncRequest): PlayerResponse
}
```

### Синхронизация

```kotlin
suspend fun syncWithServer(playerId: String) {
    try {
        val response = apiService.syncPlayer(syncRequest)
        // Обновляем локальные данные
        repository.createOrUpdatePlayer(response)
    } catch (e: Exception) {
        // Оффлайн режим - данные синхронизируются позже
    }
}
```

## 🎮 Игровая Логика

### GameViewModel

```kotlin
@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {
    
    // Состояние игры
    private val _gameState = MutableStateFlow<GameState?>(null)
    val gameState: StateFlow<GameState?> = _gameState.asStateFlow()
    
    // Инициализация доски
    fun initializeGameBoard(width: Int = 7, height: Int = 7) { }
    
    // Выбор руны
    fun selectRune(x: Int, y: Int) { }
    
    // Отправка хода
    fun submitMove() { }
}
```

### GameState

```kotlin
data class GameState(
    val boardState: Array<Array<Int>>,  // Руны на доске
    val score: Int = 0,                 // Очки
    val moves: Int = 0,                 // Ходы
    val combo: Int = 0,                 // Комбо
    val selectedRunes: List<Pair<Int, Int>> = emptyList(),
    val isAnimating: Boolean = false,
    val gameOver: Boolean = false,
    val won: Boolean = false
)
```

## 🔄 Жизненный Цикл

### Activity Lifecycle

```
onCreate()
    ↓
setContent() - Jetpack Compose
    ↓
RunaComboApp() - Composable
    ↓
Screens Navigation
    ↓
onDestroy()
```

### ViewModel Lifecycle

```
ViewModel Created
    ↓
initializeGame()
    ↓
Load Data from Repository
    ↓
Update State
    ↓
UI Recomposes
    ↓
ViewModel Cleared (onCleared)
```

## 🧪 Тестирование

### Unit Tests

```kotlin
@Test
fun testGameCombo() {
    val gameState = GameState(
        boardState = Array(7) { IntArray(7) { 1 } }
    )
    assertEquals(0, gameState.score)
}
```

### Integration Tests

```kotlin
@Test
fun testPlayerSync() = runBlocking {
    val player = repository.getPlayer("player_1")
    assertNotNull(player)
}
```

## 📊 Производительность

### Оптимизации

1. **Lazy Loading**: Данные загружаются по мере необходимости
2. **Caching**: Кэширование в Room Database
3. **Coroutines**: Асинхронные операции
4. **Compose Recomposition**: Только необходимые обновления

### Мониторинг

- Android Profiler для CPU/Memory
- Network Profiler для API запросов
- Database Inspector для БД операций

## 🔐 Безопасность

### Best Practices

1. **HTTPS Only**: Все API запросы через HTTPS
2. **ProGuard**: Обфускация кода
3. **Secure Storage**: Чувствительные данные в EncryptedSharedPreferences
4. **Input Validation**: Валидация всех входных данных

## 📈 Масштабируемость

### Расширение Функционала

1. Добавьте новый DAO для новой сущности
2. Создайте новую модель в Domain Layer
3. Расширьте Repository
4. Добавьте новый Screen в UI Layer

### Пример: Добавление Новой Сущности

```kotlin
// 1. Entity
@Entity(tableName = "new_entity")
data class NewEntity(...)

// 2. DAO
@Dao
interface NewEntityDao { }

// 3. Repository
fun getNewEntity() { }

// 4. ViewModel
fun loadNewEntity() { }

// 5. Screen
@Composable
fun NewEntityScreen() { }
```

---

**Архитектура РунаКомбо разработана для максимальной гибкости и масштабируемости!** 🚀
