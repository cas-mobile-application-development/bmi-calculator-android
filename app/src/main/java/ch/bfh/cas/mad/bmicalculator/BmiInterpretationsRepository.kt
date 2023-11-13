package ch.bfh.cas.mad.bmicalculator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class BmiInterpretationsRepository(
    private val bmiInterpretationsDao: BmiInterpretationsDao
) {
    suspend fun all(): List<String> = withContext(Dispatchers.IO) {
        val storedInterpretations = getStoredInterpretations()
        if (storedInterpretations.isNotEmpty()) {
            return@withContext storedInterpretations
        }
        val fetchedInterpretations = BmiInterpretationsClient.getAllBmiInterpretations()
        storeFetchedInterpretations(fetchedInterpretations)
        return@withContext fetchedInterpretations
    }

    private suspend fun storeFetchedInterpretations(fetchedInterpretations: List<String>) {
        fetchedInterpretations
            .map { BmiInterpretation(id = UUID.randomUUID(), text = it)}
            .forEach { bmiInterpretationsDao.insert(it) }
    }

    private suspend fun getStoredInterpretations(): List<String> =
        bmiInterpretationsDao.getAll().map { it.text }
}