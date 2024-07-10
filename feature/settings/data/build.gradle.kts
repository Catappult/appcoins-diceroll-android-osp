import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.feature.data")
}

android {
  namespace = "com.appcoins.diceroll.osp.feature.settings.data"
}

dependencies {
  projectImplementation(":core:utils")
  projectImplementation(":core:datastore")
  projectImplementation(":core:network")
  implementation(libs.androidx.datastore.preferences)
}