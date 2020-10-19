package com.github.albertopeam.data.personalization

import com.github.albertopeam.usecases.personalization.TimeRange

internal fun TimeRange.value(): String {
    return when (this) {
        TimeRange.SHORT -> "short_term"
        TimeRange.MEDIUM -> "medium_term"
        TimeRange.LONG -> "long_term"
    }
}