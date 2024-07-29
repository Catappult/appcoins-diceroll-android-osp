package com.appcoins.diceroll.osp.core.network.modules.api

import com.appcoins.diceroll.osp.core.network.modules.model.OspCallbackResultResponse
import com.appcoins.diceroll.osp.core.network.modules.model.OspUrlResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OspApi {

  @GET("osp_url/{product}")
  suspend fun getOspUrl(
    @Path("product") product: String,
    @Query("oemid") oemid: String?,
    @Query("guest_uid") guestUid: String?,
  ): OspUrlResponse

  @GET("callback_result/{order_reference}")
  suspend fun getCallbackResult(
    @Path("order_reference") orderReference: String,
  ): OspCallbackResultResponse
}