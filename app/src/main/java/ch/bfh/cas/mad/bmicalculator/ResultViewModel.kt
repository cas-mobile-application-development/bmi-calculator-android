package ch.bfh.cas.mad.bmicalculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private val bmiInterpretationsRepository = BmiInterpretationsRepository(context = application)

    fun getBmiInterpretaions(): List<String> {
        return bmiInterpretationsRepository.all()
    }
}