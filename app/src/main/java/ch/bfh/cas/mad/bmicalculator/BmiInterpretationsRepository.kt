package ch.bfh.cas.mad.bmicalculator

import android.content.Context

class BmiInterpretationsRepository {
    fun all(): List<String> =
        BmiInterpretationsClient.getAllBmiInterpretationsBlocking()
}