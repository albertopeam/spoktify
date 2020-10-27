package com.github.albertopeam.data.utils

import java.io.InputStreamReader

class MockResponseFileReader(private val fileName: String) {
    fun read(): String {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName))
        val file = reader.readText()
        reader.close()
        return file
    }
}