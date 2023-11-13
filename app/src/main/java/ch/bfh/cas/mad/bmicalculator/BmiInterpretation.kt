package ch.bfh.cas.mad.bmicalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "bmi_interpretation")
data class BmiInterpretation(
    @PrimaryKey val id: UUID,
    @ColumnInfo val text: String
)