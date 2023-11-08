package ch.bfh.cas.mad.bmicalculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultViewModel(
    private val bmiInterpretationsRepository: BmiInterpretationsRepository
) : ViewModel() {

    private var _interpretations = MutableStateFlow(emptyList<String>())
    val interpretations: StateFlow<List<String>> = _interpretations

    fun getBmiInterpretaions() {
        viewModelScope.launch {
            _interpretations.value = bmiInterpretationsRepository.all()
        }
    }
}