package com.appcoins.diceroll.osp.feature.payments.ui

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appcoins.diceroll.osp.core.navigation.destinations.DestinationArgs
import com.appcoins.diceroll.osp.core.ui.design.R
import com.appcoins.diceroll.osp.core.ui.widgets.ErrorAnimation
import com.appcoins.diceroll.osp.core.ui.widgets.LoadingAnimation
import com.appcoins.diceroll.osp.core.ui.widgets.components.DiceRollBottomSheet
import com.appcoins.diceroll.osp.feature.payments.ui.result.PaymentsResult
import com.appcoins.diceroll.osp.payments.appcoins.osp.OspLaunchCallback
import com.appcoins.diceroll.osp.payments.appcoins.osp.OspManager
import com.appcoins.diceroll.osp.payments.appcoins.osp.requireOspManagerEntryPoint

@Composable
fun PaymentProcessBottomSheetRoute(
  onDismiss: () -> Unit,
  itemId: String,
  attempts : String,
  viewModel: PaymentsViewModel = hiltViewModel(),
  ospManager: OspManager = requireOspManagerEntryPoint().ospManager,
) {
  val context = LocalContext.current as Activity

  viewModel.savedStateHandle[DestinationArgs.ITEM_ID] = itemId
  viewModel.savedStateHandle[DestinationArgs.ATTEMPTS_LEFT] = attempts

  val paymentProcessState by viewModel.paymentProcessState.collectAsStateWithLifecycle()
  val paymentResultState by viewModel.paymentResultState.collectAsStateWithLifecycle()

  DiceRollBottomSheet(onDismiss) {
    when (paymentProcessState) {
      is PaymentProcessUiState.Loading -> {
        LoadingAnimation()
      }

      is PaymentProcessUiState.Error -> {
        ErrorAnimation(
          bodyMessage = stringResource(R.string.payments_sku_error_body)
        )
      }

      is PaymentProcessUiState.NotAvailable -> {
        ErrorAnimation(
          bodyMessage = stringResource(R.string.payments_attempts_error_body)
        )
      }

      is PaymentProcessUiState.StartPayment -> {
        launchBillingOspFlow(ospManager, itemId, viewModel::launchBillingOspFlow, context)
      }

      is PaymentProcessUiState.PaymentInProgress -> {
        PaymentsResult(
          paymentResultState,
          viewModel::resetAttemptsLeft,
        )
      }
    }
  }
}

private fun launchBillingOspFlow(
  ospManager: OspManager,
  itemId: String,
  onResultPayment: (Result<String>) -> Unit,
  context: Context
) {
  val ospCallback = object : OspLaunchCallback {
    override fun onSuccess(orderReference: Result<String>) {
      onResultPayment(orderReference)
    }

    override fun onError(error: Result<String>) {
      onResultPayment(error)
    }
  }
  ospManager.startPayment(context as Activity, itemId, ospCallback)
}
