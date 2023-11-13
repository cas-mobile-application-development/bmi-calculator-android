package ch.bfh.cas.mad.bmicalculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmiInterpretationsDao {
    @Insert
    suspend fun insert(bmiInterpretation: BmiInterpretation)

    @Query("SELECT * from bmi_interpretation")
    suspend fun getAll(): List<BmiInterpretation>
}