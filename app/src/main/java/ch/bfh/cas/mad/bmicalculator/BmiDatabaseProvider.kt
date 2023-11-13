package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import androidx.room.Room

object BmiDatabaseProvider {
    fun get(context: Context): BmiDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BmiDatabase::class.java,
            name = "Bmi.db"
        ).build()
    }
}