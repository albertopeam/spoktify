package com.github.albertopeam.data.interceptors

import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import okhttp3.Interceptor
import okhttp3.Response

internal class UnauthorizedInterceptor(private val unauthorizedChallenge: UnauthorizedChallenge): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 401) {
            unauthorizedChallenge.challenge()
        }
        return response
    }

}