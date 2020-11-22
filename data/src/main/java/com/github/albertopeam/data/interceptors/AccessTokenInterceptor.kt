package com.github.albertopeam.data.interceptors

import com.github.albertopeam.data.auth.AuthenticationDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(private val authenticationDataSource: AuthenticationDataSource): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = authenticationDataSource.get()
        if (accessToken.isNotBlank()) {
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(chain.request())
    }
}