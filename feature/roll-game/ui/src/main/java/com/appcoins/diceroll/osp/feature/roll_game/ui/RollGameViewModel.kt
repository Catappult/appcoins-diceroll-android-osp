package com.appcoins.diceroll.osp.feature.roll_game.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcoins.diceroll.osp.feature.roll_game.data.usecases.GetAttemptsUseCase
import com.appcoins.diceroll.osp.feature.roll_game.data.usecases.SaveAttemptsUseCase
import com.appcoins.diceroll.osp.feature.stats.data.model.DiceRoll
import com.appcoins.diceroll.osp.feature.stats.data.usecases.SaveDiceRollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RollGameViewModel @Inject constructor(
  private val saveDiceRollUseCase: SaveDiceRollUseCase,
  private val saveAttemptsUseCase: SaveAttemptsUseCase,
  getAttemptsUseCase: GetAttemptsUseCase,
) : ViewModel() {

  internal val uiState: StateFlow<RollGameState> =
    getAttemptsUseCase()
      .map { attemptsLeft ->
        RollGameState.Success(attemptsLeft)
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RollGameState.Loading,
      )

  suspend fun saveDiceRoll(diceRoll: DiceRoll) {
    saveDiceRollUseCase(diceRoll).also { saveAttemptsUseCase(diceRoll.attemptsLeft) }
  }
}