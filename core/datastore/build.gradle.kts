plugins {
  id("diceroll.android.library")
}

android {
  namespace = "com.appcoins.diceroll.osp.core.datastore"
}

dependencies {
  implementation(libs.androidx.datastore.preferences)
}