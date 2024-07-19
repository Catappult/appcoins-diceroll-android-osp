package com.appcoins.diceroll.osp.core.network.modules.model

import com.google.gson.annotations.SerializedName

data class AttributionResponse(
    @SerializedName("package_name") val packageName: String?,
    @SerializedName("oemid") val oemId: String?,
    @SerializedName("guest_uid") val guestUid: String?
)
