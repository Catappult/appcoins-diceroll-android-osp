package com.appcoins.diceroll.osp

import android.app.Application
import com.appcoins.diceroll.osp.payments.appcoins.osp.OspManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var ospManager: OspManager

    override fun onCreate() {
        super.onCreate()

        makeAttributionRequest()
    }

    private fun makeAttributionRequest() {
        ospManager.requestAttributionForUser(this)
    }
}
