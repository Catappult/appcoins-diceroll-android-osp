package com.appcoins.diceroll.osp.core.utils

import kotlinx.coroutines.flow.MutableSharedFlow

object ActivityResultStream : EventBusInterface {

    override var eventFlow = MutableSharedFlow<Any>()
}
