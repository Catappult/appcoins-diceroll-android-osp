package com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository

import com.appcoins.diceroll.osp.core.network.modules.model.AttributionResponse
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.model.Attribution

fun AttributionResponse.toAttribution(): Attribution =
    Attribution(
        packageName = packageName,
        oemId = oemId,
        guestUid = guestUid
    )
