package com.github.albertopeam.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ServiceBuilder(baseUrl: String, unauthorizedInterceptor: Interceptor) {
    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(unauthorizedInterceptor)
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