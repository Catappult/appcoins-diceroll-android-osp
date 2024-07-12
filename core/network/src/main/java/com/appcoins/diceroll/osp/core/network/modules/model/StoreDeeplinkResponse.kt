package com.appcoins.diceroll.osp.core.network.modules.model

import com.google.gson.annotations.SerializedName

data class StoreDeeplinkResponse(
  @SerializedName("deeplink") val url: String,
)