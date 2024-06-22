package com.ryan.agriaid.data.local.clasification

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TFLiteModelInterpreter(context: Context, assetFilePath: String) {
    private val interpreter: Interpreter

    init {
        val model = loadModelFile(context, assetFilePath)
        val options = Interpreter.Options()
        interpreter = Interpreter(model, options)
    }

    private fun loadModelFile(context: Context, modelFileName: String): ByteBuffer {
        val assetManager = context.assets
        assetManager.open(modelFileName).use { inputStream ->
            val modelBytes = inputStream.readBytes()
            return modelBytes.toByteBuffer()
        }
    }

    fun classify(inputs: Array<FloatArray>): FloatArray {
        val outputs = Array(1) { FloatArray(NUM_CLASSES) }
        interpreter.run(inputs, outputs)
        return outputs[0]
    }

    companion object {
        private const val NUM_CLASSES = 22
    }
}

fun ByteArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(size)
    byteBuffer.order(ByteOrder.nativeOrder())
    byteBuffer.put(this)
    byteBuffer.rewind()
    return byteBuffer
}