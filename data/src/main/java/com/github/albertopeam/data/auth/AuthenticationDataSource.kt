package com.github.albertopeam.data.auth

interface AuthenticationDataSource {
    fun set(accessToken: String)
    fun get(): String
}