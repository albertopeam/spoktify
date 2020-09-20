package com.github.albertopeam.data.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor(private val unauthorized: ((Unit) -> Unit)): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 401) {
            unauthorized(Unit)
        }
        return response
    }

}