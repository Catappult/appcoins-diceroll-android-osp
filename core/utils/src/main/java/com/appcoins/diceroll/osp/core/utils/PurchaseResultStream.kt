package com.appcoins.diceroll.osp.core.utils

import kotlinx.coroutines.flow.MutableSharedFlow

object PurchaseResultStream : EventBusInterface {

    override var eventFlow = MutableSharedFlow<Any>()
}
