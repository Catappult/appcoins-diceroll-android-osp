package com.appcoins.diceroll.osp.core.navigation.destinations

import androidx.core.net.toUri
import com.appcoins.diceroll.osp.core.utils.diceRollPackage

/**
 * Represents the Deep Links to implicit navigate through the application, like PendingIntent.
 */
object DestinationDeeplink {

  private val baseUri = "app://${diceRollPackage}".toUri()

}