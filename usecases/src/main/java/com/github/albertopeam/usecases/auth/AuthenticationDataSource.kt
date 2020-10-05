package com.github.albertopeam.usecases.auth

interface AuthenticationDataSource {
    fun set(accessToken: String)
    fun get(): String
}