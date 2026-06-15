# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Gson
-keep class com.google.gson.** { *; }
-keepattributes EnclosedInnerClasses
-keepattributes InnerClasses

# Okhttp
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Room
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keepattributes *Annotation*

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep data classes
-keep class com.runacombo.data.api.** { *; }
-keep class com.runacombo.data.db.entity.** { *; }
-keep class com.runacombo.domain.model.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }

# Compose
-keep class androidx.compose.** { *; }

# Generic signature of kept members
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
-dontwarn com.google.common.collect.Maps
-dontwarn com.google.common.collect.Lists
-dontwarn com.google.common.collect.Iterables

# Application classes
-keep class com.runacombo.** { *; }
-keepclassmembers class com.runacombo.** { *; }
