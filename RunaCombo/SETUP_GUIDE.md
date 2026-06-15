# Руководство по Установке РунаКомбо

## 📋 Требования

- **Android Studio**: версия 2023.1 или выше
- **JDK**: версия 17 или выше
- **Android SDK**: API 34 (целевой), API 24 (минимальный)
- **Gradle**: 8.0 или выше
- **Оперативная память**: минимум 8 ГБ

## 🚀 Пошаговая Установка

### Шаг 1: Подготовка Android Studio

1. Скачайте и установите **Android Studio** с официального сайта
2. При первом запуске установите необходимые компоненты:
   - Android SDK 34
   - Android SDK Platform Tools
   - Android Emulator
   - Intel x86 Emulator Accelerator (HAXM)

### Шаг 2: Открытие Проекта

1. Откройте Android Studio
2. Выберите **File → Open**
3. Навигируйте к папке `RunaCombo` и откройте её
4. Дождитесь загрузки проекта (может занять 2-5 минут)

### Шаг 3: Синхронизация Gradle

1. Android Studio автоматически предложит синхронизировать Gradle
2. Нажмите **Sync Now** в появившемся баннере
3. Дождитесь завершения синхронизации

Если синхронизация не началась автоматически:
- Выберите **File → Sync Project with Gradle Files**

### Шаг 4: Сборка Проекта

1. Выберите **Build → Rebuild Project**
2. Дождитесь завершения сборки (может занять 3-10 минут)
3. Проверьте, что нет ошибок в консоли

### Шаг 5: Запуск на Эмуляторе

#### Вариант A: Использование встроенного эмулятора

1. Выберите **Tools → Device Manager**
2. Нажмите **Create Device**
3. Выберите устройство (например, Pixel 6)
4. Выберите API 34
5. Завершите создание устройства
6. Нажмите кнопку запуска рядом с устройством

#### Вариант B: Запуск приложения

1. Выберите **Run → Run 'app'**
2. Выберите созданное устройство
3. Нажмите **OK**
4. Дождитесь установки и запуска приложения

### Шаг 6: Запуск на Реальном Устройстве

1. Подключите Android-устройство к компьютеру через USB
2. Включите режим разработчика на устройстве:
   - Settings → About Phone → нажмите 7 раз на Build Number
   - Settings → Developer Options → включите USB Debugging
3. Выберите **Run → Run 'app'**
4. Выберите ваше устройство из списка
5. Нажмите **OK**

## 🔧 Конфигурация

### Изменение API URL

Отредактируйте файл `app/src/main/kotlin/com/runacombo/di/AppModule.kt`:

```kotlin
@Singleton
@Provides
fun provideApiService(okHttpClient: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .baseUrl("https://ваш-api.com/") // Измените URL здесь
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
```

### Изменение Минимального API

В файле `app/build.gradle.kts`:

```kotlin
defaultConfig {
    minSdk = 24  // Измените здесь
    targetSdk = 34
}
```

## 🐛 Решение Проблем

### Проблема: "Gradle sync failed"

**Решение:**
1. Выберите **File → Invalidate Caches**
2. Выберите **Invalidate and Restart**
3. Повторите синхронизацию

### Проблема: "SDK not found"

**Решение:**
1. Выберите **File → Project Structure**
2. В разделе SDK Location укажите путь к Android SDK
3. Нажмите **OK**

### Проблема: "Emulator not starting"

**Решение:**
1. Убедитесь, что виртуализация включена в BIOS
2. Переустановите эмулятор
3. Используйте реальное устройство вместо эмулятора

### Проблема: "Build failed"

**Решение:**
1. Очистите проект: **Build → Clean Project**
2. Перестройте: **Build → Rebuild Project**
3. Удалите папку `.gradle` и повторите

## 📦 Сборка APK

### Debug APK

1. Выберите **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. Дождитесь завершения сборки
3. APK будет находиться в `app/build/outputs/apk/debug/`

### Release APK

1. Выберите **Build → Build Bundle(s) / APK(s) → Build Bundle(s)**
2. Выберите конфигурацию Release
3. Подпишите APK вашим ключом
4. Bundle будет находиться в `app/build/outputs/bundle/release/`

## 📱 Тестирование

### Базовое Тестирование

1. Запустите приложение
2. Проверьте все экраны:
   - Splash Screen
   - Main Screen
   - Gameplay Screen
   - Heroes Screen
   - Runes Screen
   - Shop Screen

### Тестирование Производительности

1. Откройте **Profiler** в Android Studio
2. Запустите приложение
3. Мониторьте:
   - CPU Usage
   - Memory Usage
   - Network Traffic

## 🔐 Безопасность

### Перед Релизом

1. Включите ProGuard/R8 obfuscation
2. Удалите все debug логи
3. Используйте HTTPS для API
4. Храните чувствительные данные безопасно

## 📚 Дополнительные Ресурсы

- [Android Developer Documentation](https://developer.android.com/)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Retrofit Documentation](https://square.github.io/retrofit/)

## 💡 Советы

- Используйте **Logcat** для отладки
- Включите **Strict Mode** для поиска проблем производительности
- Регулярно проверяйте **Lint** предупреждения
- Используйте **Profiler** для оптимизации

## 📞 Поддержка

Если у вас возникли проблемы:

1. Проверьте логи в **Logcat**
2. Поищите решение в **Stack Overflow**
3. Обратитесь в поддержку проекта

---

**Удачи в разработке РунаКомбо!** 🚀✨
