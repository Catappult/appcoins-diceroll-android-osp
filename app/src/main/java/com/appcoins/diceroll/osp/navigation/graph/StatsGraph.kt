package com.appcoins.diceroll.osp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.appcoins.diceroll.osp.feature.stats.ui.navigation.navigateToRollDetailsStatsScreen
import com.appcoins.diceroll.osp.feature.stats.ui.navigation.rollDetailsStatsRoute
import com.appcoins.diceroll.osp.feature.stats.ui.navigation.statsRoute

internal fun NavGraphBuilder.statsGraph(navController: NavHostController) {
  statsRoute(onDetailsClick = {
    navController.navigateToRollDetailsStatsScreen()
  })
  rollDetailsStatsRoute()
}