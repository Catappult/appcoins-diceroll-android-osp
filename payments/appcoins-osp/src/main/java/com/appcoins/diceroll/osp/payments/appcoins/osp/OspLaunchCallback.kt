package com.appcoins.diceroll.osp.payments.appcoins.osp

interface OspLaunchCallback {
  fun onSuccess(orderReference: Result<String>)
  fun onError(error: Result<String>)
}
