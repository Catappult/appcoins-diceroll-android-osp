package com.appcoins.diceroll.osp.feature.stats.data.usecases

import com.appcoins.diceroll.osp.feature.stats.data.model.DiceRoll
import com.appcoins.diceroll.osp.feature.stats.data.repository.DiceRollRepository
import javax.inject.Inject

class SaveDiceRollUseCase @Inject constructor(private val diceRollRepository: DiceRollRepository) {

  suspend operator fun invoke(diceRoll: DiceRoll) = diceRollRepository.saveDiceRoll(diceRoll)
}