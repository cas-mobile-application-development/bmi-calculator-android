package ch.bfh.cas.mad.bmicalculator

import android.content.Context

class BmiInterpretationsRepository(
    private val context: Context
) {
    fun all(): List<String> =
        context.resources.getStringArray(R.array.interpretation_bmi).toList()
}