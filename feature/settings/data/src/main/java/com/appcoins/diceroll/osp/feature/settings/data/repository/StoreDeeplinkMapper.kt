package com.appcoins.diceroll.osp.feature.settings.data.repository

import com.appcoins.diceroll.osp.core.network.modules.model.StoreDeeplinkResponse
import com.appcoins.diceroll.osp.feature.settings.data.model.StoreDeeplink

fun StoreDeeplinkResponse.toStoreDeeplink(): StoreDeeplink {
  return StoreDeeplink(
    url = this.url,
  )
}
