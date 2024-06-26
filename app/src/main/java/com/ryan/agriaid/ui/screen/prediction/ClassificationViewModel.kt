package com.ryan.agriaid.ui.screen.prediction

import androidx.lifecycle.ViewModel
import com.ryan.agriaid.data.local.clasification.TFLiteModelInterpreter

class ClassificationViewModel(
    private val tfliteModelInterpreter: TFLiteModelInterpreter
) : ViewModel() {

    private val labels = listOf(
        "padi", "jagung", "buncis", "kacang_merah", "kacang_gude", "kacang_matik", "kacang_hijau", "kacang_hitam",
        "kacang_lentis", "delima", "pisang", "mangga", "anggur", "semangka", "melon", "apel", "jeruk", "pepaya",
        "kelapa", "kapas", "rami", "kopi"
    )

    fun performInference(inputs: Array<FloatArray>): List<String> {
        val outputProbabilities = tfliteModelInterpreter.classify(inputs)
        val top4Indices = outputProbabilities
            .mapIndexed { index, probability -> index to probability }
            .sortedByDescending { it.second }
            .take(4)
            .map { it.first }
        return top4Indices.map { labels[it] }
    }
}
