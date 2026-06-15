package com.runacombo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runacombo.data.db.entity.PlayerEntity
import com.runacombo.data.repository.GameRepository
import com.runacombo.domain.model.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _playerState = MutableStateFlow<PlayerEntity?>(null)
    val playerState: StateFlow<PlayerEntity?> = _playerState.asStateFlow()

    private val _gameState = MutableStateFlow<GameState?>(null)
    val gameState: StateFlow<GameState?> = _gameState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        initializeGame()
    }

    private fun initializeGame() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Initialize default data
                repository.initializeDefaultHeroes()
                repository.initializeDefaultRunes()
                repository.initializeDefaultLocations()

                // Get or create player
                var player = repository.getPlayer("player_1")
                if (player == null) {
                    player = PlayerEntity()
                    repository.createOrUpdatePlayer(player)
                }

                _playerState.value = player

                // Sync with server
                repository.syncWithServer("player_1")

                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun addCoins(amount: Long) {
        viewModelScope.launch {
            repository.addCoins("player_1", amount)
            val updated = repository.getPlayer("player_1")
            _playerState.value = updated
        }
    }

    fun addCrystals(amount: Long) {
        viewModelScope.launch {
            repository.addCrystals("player_1", amount)
            val updated = repository.getPlayer("player_1")
            _playerState.value = updated
        }
    }

    fun updateEnergy(energy: Int) {
        viewModelScope.launch {
            repository.updateEnergy("player_1", energy)
            val updated = repository.getPlayer("player_1")
            _playerState.value = updated
        }
    }

    fun initializeGameBoard(width: Int = 7, height: Int = 7) {
        val board = Array(height) { IntArray(width) { (1..6).random() } }
        _gameState.value = GameState(
            boardState = board,
            score = 0,
            moves = 20
        )
    }

    fun selectRune(x: Int, y: Int) {
        val current = _gameState.value ?: return
        val selected = current.selectedRunes.toMutableList()

        if (selected.contains(Pair(x, y))) {
            selected.remove(Pair(x, y))
        } else {
            selected.add(Pair(x, y))
        }

        _gameState.value = current.copy(selectedRunes = selected)
    }

    fun submitMove() {
        viewModelScope.launch {
            val current = _gameState.value ?: return@launch
            if (current.selectedRunes.size < 3) return@launch

            // Calculate combo and damage
            val comboMultiplier = when (current.selectedRunes.size) {
                3 -> 1.0f
                4 -> 1.5f
                5 -> 2.0f
                else -> 2.5f
            }

            val damage = (100 * comboMultiplier).toInt()
            val newScore = current.score + damage
            val newMoves = current.moves - 1

            _gameState.value = current.copy(
                score = newScore,
                moves = newMoves,
                selectedRunes = emptyList(),
                combo = current.combo + 1,
                gameOver = newMoves <= 0
            )
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
