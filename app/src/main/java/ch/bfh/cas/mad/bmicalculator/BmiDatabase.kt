package ch.bfh.cas.mad.bmicalculator

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BmiInterpretation::class], version = 1)
abstract class BmiDatabase: RoomDatabase() {
    abstract fun getBmiInterpretationsDao(): BmiInterpretationsDao
}