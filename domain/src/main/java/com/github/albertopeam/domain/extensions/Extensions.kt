package com.github.albertopeam.domain.extensions

fun <T: Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

fun <T: Any> T?.unwrap(): T {
    return this!!
}