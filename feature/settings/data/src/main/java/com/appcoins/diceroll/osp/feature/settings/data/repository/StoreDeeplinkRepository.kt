package com.appcoins.diceroll.osp.feature.settings.data.repository

import com.appcoins.diceroll.osp.core.network.modules.api.StoreDeeplinkApi
import com.appcoins.diceroll.osp.feature.settings.data.model.StoreDeeplink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreDeeplinkRepository @Inject constructor(private val storeDeeplinkApi: StoreDeeplinkApi) {

  suspend fun getStoreDeeplinkUrl(appPackage: String, storePackage: String?): Result<StoreDeeplink> {
    return runCatching {
      withContext(Dispatchers.IO) {
        storeDeeplinkApi.getDeeplinkUrl(appPackage = appPackage, storePackage = storePackage).toStoreDeeplink()
      }
    }
  }
}