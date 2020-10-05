package com.github.albertopeam.domain.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.iso8601(): String{
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(this)
}