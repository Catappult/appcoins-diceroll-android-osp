package com.appcoins.diceroll.osp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appcoins.diceroll.osp.core.db.model.DiceRollDao
import com.appcoins.diceroll.osp.core.db.model.DiceRollEntity

@Database(
  entities = [DiceRollEntity::class],
  version = 1
)
abstract class DiceRollDatabase : RoomDatabase() {
  abstract fun diceRollDao(): DiceRollDao
}