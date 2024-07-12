package com.appcoins.diceroll.osp.core.network.modules.model

import com.google.gson.annotations.SerializedName

data class OspUrlResponse(
  val url: String,
  @SerializedName("order_reference") val orderReference: String?
)