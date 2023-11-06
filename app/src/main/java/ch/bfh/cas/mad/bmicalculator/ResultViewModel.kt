package ch.bfh.cas.mad.bmicalculator

import androidx.lifecycle.ViewModel

class ResultViewModel(
    private val bmiInterpretationsRepository: BmiInterpretationsRepository
) : ViewModel() {
    fun getBmiInterpretaions(): List<String> {
        return bmiInterpretationsRepository.all()
    }
}