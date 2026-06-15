# 🚀 Быстрый Старт РунаКомбо

## ⚡ За 5 минут до первого запуска

### 1️⃣ Откройте проект в Android Studio

```bash
# Клонируйте или откройте папку
File → Open → выберите RunaCombo
```

### 2️⃣ Дождитесь синхронизации Gradle

- Android Studio автоматически синхронизирует зависимости
- Это может занять 2-5 минут при первом запуске

### 3️⃣ Создайте эмулятор (если нужен)

```
Tools → Device Manager → Create Device → Pixel 6 → API 34 → Finish
```

### 4️⃣ Запустите приложение

```
Run → Run 'app' → выберите устройство → OK
```

### 5️⃣ Готово! 🎉

Приложение должно запуститься с экрана заставки.

---

## 📁 Основные Файлы Проекта

```
RunaCombo/
├── app/src/main/kotlin/com/runacombo/
│   ├── MainActivity.kt              ← Точка входа
│   ├── ui/screen/
│   │   ├── SplashScreen.kt          ← Заставка
│   │   ├── MainScreen.kt            ← Главное меню
│   │   ├── GamePlayScreen.kt        ← Игра
│   │   ├── HeroesScreen.kt          ← Герои
│   │   ├── RunesScreen.kt           ← Руны
│   │   ├── ShopScreen.kt            ← Магазин
│   │   └── WorldMapScreen.kt        ← Карта
│   ├── presentation/viewmodel/
│   │   └── GameViewModel.kt         ← Логика игры
│   ├── data/repository/
│   │   └── GameRepository.kt        ← Работа с данными
│   ├── game/
│   │   └── GameEngine.kt            ← Движок игры
│   └── di/
│       └── AppModule.kt             ← Зависимости
├── app/build.gradle.kts             ← Конфигурация
├── README.md                         ← Документация
├── SETUP_GUIDE.md                   ← Подробная установка
├── ARCHITECTURE.md                  ← Архитектура
└── QUICK_START.md                   ← Этот файл
```

---

## 🎮 Как Играть

1. **Нажмите "ИГРАТЬ"** на главном экране
2. **Выбирайте руны** - кликайте на руны на доске
3. **Создавайте комбо** - соединяйте 3+ одинаковых руны
4. **Подтверждайте** - нажимайте кнопку "Подтвердить комбо"
5. **Побеждайте** - достигните целевого количества очков

---

## 🔧 Первые Изменения

### Измените API URL

**Файл:** `app/src/main/kotlin/com/runacombo/di/AppModule.kt`

```kotlin
.baseUrl("https://ваш-api.com/") // Измените здесь
```

### Измените Цвета

**Файл:** `app/src/main/kotlin/com/runacombo/ui/theme/Color.kt`

```kotlin
val Primary = Color(0xFFFFD700)  // Золотой
val Accent = Color(0xFF00d4ff)   // Голубой
```

### Добавьте Нового Героя

**Файл:** `app/src/main/kotlin/com/runacombo/domain/model/Hero.kt`

```kotlin
val NEW_HERO = Hero(
    id = 7,
    name = "Новый Герой",
    // ... остальные параметры
)
```

---

## 🐛 Частые Проблемы

### ❌ "Gradle sync failed"

**Решение:**
```
File → Invalidate Caches → Invalidate and Restart
```

### ❌ "Emulator not starting"

**Решение:**
- Используйте реальное устройство
- Или переустановите эмулятор

### ❌ "Build failed"

**Решение:**
```
Build → Clean Project
Build → Rebuild Project
```

---

## 📊 Структура Экранов

```
Splash Screen (3 сек)
    ↓
Main Screen
    ├─→ GamePlay Screen
    ├─→ Heroes Screen
    ├─→ Runes Screen
    ├─→ Shop Screen
    └─→ World Map Screen
```

---

## 💡 Советы

- 🔍 Используйте **Logcat** для отладки (View → Tool Windows → Logcat)
- 📱 Тестируйте на реальном устройстве для лучшей производительности
- 🎨 Все цвета и стили в `ui/theme/`
- 🎮 Игровая логика в `game/GameEngine.kt`
- 💾 База данных автоматически создается при первом запуске

---

## 📚 Дополнительные Ресурсы

- [Android Developer](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Room Database](https://developer.android.com/training/data-storage/room)

---

## ✅ Чек-лист для Первого Запуска

- [ ] Android Studio установлена
- [ ] JDK 17+ установлена
- [ ] Проект открыт в Android Studio
- [ ] Gradle синхронизирован
- [ ] Эмулятор создан или устройство подключено
- [ ] Приложение запущено
- [ ] Вы видите Splash Screen
- [ ] Вы видите Main Screen
- [ ] Вы можете нажать "ИГРАТЬ"

---

**Готовы к разработке? Начните с изменения цветов и добавления своих героев!** 🚀✨

Если у вас возникли вопросы, обратитесь к `SETUP_GUIDE.md` или `ARCHITECTURE.md`.
