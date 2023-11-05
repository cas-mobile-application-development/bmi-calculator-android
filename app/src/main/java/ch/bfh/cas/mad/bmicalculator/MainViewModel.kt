package ch.bfh.cas.mad.bmicalculator

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    fun calculateBmi(heightInCm: Int, weightInKg: Int): Double {
        val heightInMeters = heightInCm / 100.0
        return weightInKg / (heightInMeters * heightInMeters)
    }
}