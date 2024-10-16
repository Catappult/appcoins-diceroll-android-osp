import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.library.compose")
}

android {
  namespace = "com.appcoins.diceroll.osp.payments.appcoins_osp"
}

dependencies {
  projectImplementation(":core:network")
  projectImplementation(":core:utils")
  implementation(libs.network.retrofit)
}