package ch.bfh.cas.mad.bmicalculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(
    private val bmiInterpretationsRepository: BmiInterpretationsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultViewModel(bmiInterpretationsRepository) as T
    }
}