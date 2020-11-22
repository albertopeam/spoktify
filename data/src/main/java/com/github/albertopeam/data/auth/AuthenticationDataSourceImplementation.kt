package com.github.albertopeam.data.auth

import android.content.SharedPreferences

class AuthenticationDataSourceImplementation(private val preferences: SharedPreferences):
    AuthenticationDataSource {
    private val key: String = "accessToken"

    override fun set(accessToken: String) {
        preferences.edit().putString(key, accessToken).apply()
    }

    override fun get(): String {
        return preferences.getString(key, "") ?: ""
    }
}