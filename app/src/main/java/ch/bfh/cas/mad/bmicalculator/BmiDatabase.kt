package ch.bfh.cas.mad.bmicalculator

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BmiInterpretation::class],
    autoMigrations = [AutoMigration(from = 1, to = 2)],
    version = 2,
    exportSchema = true
)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun getBmiInterpretationsDao(): BmiInterpretationsDao
}