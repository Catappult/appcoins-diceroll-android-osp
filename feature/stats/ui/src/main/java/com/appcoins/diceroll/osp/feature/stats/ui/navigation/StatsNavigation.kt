package com.appcoins.diceroll.osp.feature.stats.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.appcoins.diceroll.osp.core.navigation.destinations.Destinations
import com.appcoins.diceroll.osp.core.navigation.buildDestinationRoute
import com.appcoins.diceroll.osp.core.navigation.navigateToDestination
import com.appcoins.diceroll.osp.feature.stats.data.model.DiceRoll
import com.appcoins.diceroll.osp.feature.stats.ui.StatsRoute

fun NavController.navigateToStatsScreen(navOptions: NavOptions) {
  this.navigateToDestination(
    destination = Destinations.Screen.Stats,
    navOptions = navOptions
  )
}

fun NavGraphBuilder.statsRoute(onDetailsClick: (List<DiceRoll>) -> Unit) {
  this.buildDestinationRoute(
    destination = Destinations.Screen.Stats,
  ) {
    StatsRoute(onDetailsClick)
  }
}
