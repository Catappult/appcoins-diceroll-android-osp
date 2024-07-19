import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.library.compose")
}

android {
  namespace = "com.appcoins.diceroll.osp.payments.appcoins_osp"
}

dependencies {
  projectImplementation(":core:datastore")
  projectImplementation(":core:network")
  projectImplementation(":core:utils")
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.network.retrofit)
}