package com.appcoins.diceroll.osp.core.utils

val ospUrl = if(BuildConfig.DEBUG) "https://osp.diceroll.dev.catappult.io" else "https://osp.diceroll.catappult.io"
const val storeDeepLinkUrl = "https://store-link-mapper.aptoide.com"

val diceRollPackage = if(BuildConfig.DEBUG) "com.appcoins.diceroll.osp.dev" else "com.appcoins.diceroll.osp"
val walletPackage = if(BuildConfig.DEBUG) "com.appcoins.wallet.dev" else "com.appcoins.wallet"
