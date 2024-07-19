package com.appcoins.diceroll.osp.core.network.modules.api

import com.appcoins.diceroll.osp.core.network.modules.model.AttributionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AttributionApi {

    @GET("attribution")
    suspend fun getAttributionForUser(
        @Query("package_name") packageName: String,
        @Query("user_os") userOs: String,
        @Query("user_device") userDevice: String
    ): AttributionResponse
}