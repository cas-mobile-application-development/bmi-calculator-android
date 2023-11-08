package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BmiInterpretationsRepository {
    suspend fun all(): List<String> = withContext(Dispatchers.IO) {
        BmiInterpretationsClient.getAllBmiInterpretations()
    }
}