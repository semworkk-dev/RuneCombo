package com.runacombo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.runacombo.presentation.viewmodel.GameViewModel
import com.runacombo.ui.screen.*
import com.runacombo.ui.theme.Background
import com.runacombo.ui.theme.RunaComboTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunaComboTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    RunaComboApp()
                }
            }
        }
    }
}

@Composable
fun RunaComboApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    val viewModel: GameViewModel = hiltViewModel()

    Crossfade(
        targetState = currentScreen,
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            Screen.Splash -> {
                SplashScreen(
                    onNavigateToMain = { currentScreen = Screen.Main }
                )
            }
            Screen.Main -> {
                MainScreen(
                    viewModel = viewModel,
                    onPlayClick = { currentScreen = Screen.GamePlay },
                    onHeroesClick = { currentScreen = Screen.Heroes },
                    onRunesClick = { currentScreen = Screen.Runes },
                    onShopClick = { currentScreen = Screen.Shop }
                )
            }
            Screen.GamePlay -> {
                GamePlayScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = Screen.Main }
                )
            }
            Screen.Heroes -> {
                HeroesScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = Screen.Main }
                )
            }
            Screen.Runes -> {
                RunesScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = Screen.Main }
                )
            }
            Screen.Shop -> {
                ShopScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = Screen.Main }
                )
            }
            Screen.WorldMap -> {
                WorldMapScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = Screen.Main }
                )
            }
        }
    }
}

sealed class Screen {
    object Splash : Screen()
    object Main : Screen()
    object GamePlay : Screen()
    object Heroes : Screen()
    object Runes : Screen()
    object Shop : Screen()
    object WorldMap : Screen()
}
