import com.appcoins.diceroll.osp.convention.extensions.projectImplementation

plugins {
  id("diceroll.android.library")
  id("diceroll.room")
}

android {
  namespace = "com.appcoins.diceroll.osp.core.db"
}

dependencies {
  projectImplementation(":core:utils")
}