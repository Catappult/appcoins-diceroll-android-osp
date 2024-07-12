import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.feature.data")
}

android {
  namespace = "com.appcoins.diceroll.osp.feature.stats.data"
}

dependencies {
  projectImplementation(":core:utils")
  projectImplementation(":core:db")
  implementation(libs.androidx.datastore.preferences)
}