package com.github.albertopeam.data.service

import com.github.albertopeam.data.interceptors.AccessTokenInterceptor
import com.github.albertopeam.data.interceptors.UnauthorizedInterceptor
import com.github.albertopeam.usecases.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder(baseUrl: String, unauthorizedChallenge: UnauthorizedChallenge, authenticationDataSource: AuthenticationDataSource) {
    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val unauthorizedInterceptor = UnauthorizedInterceptor(unauthorizedChallenge)
    private val accessTokenInterceptor = AccessTokenInterceptor(authenticationDataSource)
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(unauthorizedInterceptor)
        .addInterceptor(accessTokenInterceptor)
        .addInterceptor(loggingInterceptor)
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