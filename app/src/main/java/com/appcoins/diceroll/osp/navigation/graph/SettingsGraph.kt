package com.appcoins.diceroll.osp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.appcoins.diceroll.osp.feature.settings.ui.navigation.settingsRoute

internal fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
  settingsRoute { navController.navigateUp() }
}