package com.appcoins.diceroll.feature.payments.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcoins.diceroll.core.navigation.destinations.DestinationArgs
import com.appcoins.diceroll.core.utils.extensions.takeUntilTimeout
import com.appcoins.diceroll.feature.payments.ui.result.PaymentsResultUiState
import com.appcoins.diceroll.feature.roll_game.data.DEFAULT_ATTEMPTS_NUMBER
import com.appcoins.diceroll.feature.roll_game.data.usecases.ResetAttemptsUseCase
import com.appcoins.diceroll.payments.appcoins.osp.data.model.OspCallbackState
import com.appcoins.diceroll.payments.appcoins.osp.data.usecases.PollOspCallbackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.produceIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@HiltViewModel
class PaymentsViewModel @Inject constructor(
  private val resetAttemptsUseCase: ResetAttemptsUseCase,
  private val pollOspCallbackUseCase: PollOspCallbackUseCase,
  val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val itemId = savedStateHandle.get<String>(DestinationArgs.ITEM_ID)
  private val attempts = savedStateHandle.get<String>(DestinationArgs.ATTEMPTS_LEFT)

  private val _paymentProcessState =
    MutableStateFlow<PaymentProcessUiState>(PaymentProcessUiState.Loading)
  internal val paymentProcessState: StateFlow<PaymentProcessUiState> get() = _paymentProcessState

  private val _paymentResultState =
    MutableStateFlow<PaymentsResultUiState>(PaymentsResultUiState.Initialized)
  internal val paymentResultState: StateFlow<PaymentsResultUiState> get() = _paymentResultState

  init {
    when {
      itemId == null || attempts == null -> {
        _paymentProcessState.value = PaymentProcessUiState.Error
      }

      attempts == DEFAULT_ATTEMPTS_NUMBER.toString() -> {
        _paymentProcessState.value = PaymentProcessUiState.NotAvailable
      }

      else -> {
        _paymentProcessState.value = PaymentProcessUiState.StartPayment
      }
    }
  }

  fun launchBillingOspFlow(orderReference: Result<String>) {
    _paymentProcessState.value = PaymentProcessUiState.PaymentInProgress
    _paymentResultState.value = PaymentsResultUiState.Loading
    observeOspCallback(orderReference)
  }

  suspend fun resetAttemptsLeft() {
    resetAttemptsUseCase()
  }

  private fun observeOspCallback(orderReference: Result<String>) {
    orderReference.fold(
      onSuccess = { reference ->
        pollOspCallbackUseCase(reference)
          .map { callbackResult ->
            when (callbackResult.status) {
              OspCallbackState.COMPLETED -> PaymentsResultUiState.Success
              OspCallbackState.CANCELED -> PaymentsResultUiState.UserCanceled
              OspCallbackState.FAILED -> PaymentsResultUiState.Failed
              OspCallbackState.PENDING -> PaymentsResultUiState.Loading
            }
          }
          .onEach { paymentResultUiState -> _paymentResultState.value = paymentResultUiState }
          .takeUntilTimeout(10.minutes)
          .catch {
            _paymentResultState.value = PaymentsResultUiState.Failed
          }
          .produceIn(viewModelScope)
      },
      onFailure = {
        _paymentResultState.value = PaymentsResultUiState.Failed
      }
    )
  }
}
