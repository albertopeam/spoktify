package com.github.albertopeam.usecases.exceptions

import java.lang.RuntimeException

class DataException(val code: Int, override val message: String): RuntimeException() {

}