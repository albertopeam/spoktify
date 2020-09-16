package com.github.albertopeam.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ServiceBuilder(private val baseUrl: String) {
    private val httpClient = OkHttpClient.Builder().build()
    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()

    fun <S> build(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}