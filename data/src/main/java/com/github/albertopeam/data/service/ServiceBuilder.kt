package com.github.albertopeam.data.service

import com.github.albertopeam.data.service.interceptor.UnauthorizedInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ServiceBuilder(private val baseUrl: String, private val unauthorized: ((Unit) -> Unit)) {
    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(UnauthorizedInterceptor(unauthorized))
        .build()
    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()

    fun <S> build(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}