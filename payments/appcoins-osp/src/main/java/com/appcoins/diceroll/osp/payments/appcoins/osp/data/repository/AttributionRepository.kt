package com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository

import com.appcoins.diceroll.osp.core.network.modules.api.AttributionApi
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.model.Attribution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AttributionRepository @Inject constructor(
    private val attributionApi: AttributionApi,
) {

    suspend fun requestAttributionForUser(
        packageName: String,
        userOs: String,
        userDevice: String
    ): Result<Attribution> =
        runCatching {
            withContext(Dispatchers.IO) {
                attributionApi.getAttributionForUser(
                    packageName = packageName,
                    userOs = userOs,
                    userDevice = userDevice
                ).toAttribution()
            }
        }
}
