import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.library")
}

android {
  namespace = "com.appcoins.diceroll.osp.core.network"
}

dependencies {
  projectImplementation(":core:utils")
  implementation(libs.bundles.network)
}