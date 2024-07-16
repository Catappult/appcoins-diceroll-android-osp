package com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository

import com.appcoins.diceroll.osp.core.network.modules.api.OspApi
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.model.OspCallbackResult
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.model.OspUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OspRepository @Inject constructor(
  private val ospApi: OspApi,
) {

  suspend fun getOspUrl(product: String, oemId: String?, guestUid: String?): Result<OspUrl> {
    return runCatching {
      withContext(Dispatchers.IO) {
        ospApi.getOspUrl(product = product, oemId = oemId, guestUid = guestUid).toOspUrl()
      }
    }
  }

  fun observeCallbackResult(orderReference: String): Flow<OspCallbackResult> {
    return flow {
      while (true) {
        emit(ospApi.getCallbackResult(orderReference = orderReference).toOspCallbackResult())
      }
    }.flowOn(Dispatchers.IO)
  }
}
