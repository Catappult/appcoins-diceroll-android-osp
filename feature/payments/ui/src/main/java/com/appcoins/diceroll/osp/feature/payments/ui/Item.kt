package com.appcoins.diceroll.osp.feature.payments.ui

import java.io.Serializable

/**
 * Payment item in game to represent and match a given SKU.
 */
sealed class Item  {
  data class Attempts(val currentAttempts: Int) : Item()
}

/**
 * Map a [Item] to a SKU string from Catappult if possible.
 */
fun Item.toSku(): String = when (this) {
  is Item.Attempts -> "attempts"
}
