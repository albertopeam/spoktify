package com.github.albertopeam.data.service

import com.github.albertopeam.data.interceptors.AccessTokenInterceptor
import com.github.albertopeam.data.interceptors.UnauthorizedInterceptor
import com.github.albertopeam.usecases.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder(private val baseUrl: String,
                     private val unauthorizedChallenge: UnauthorizedChallenge,
                     private val authenticationDataSource: AuthenticationDataSource,
                     private val useLoginInterceptor: Boolean = true) {
    fun <S> build(serviceClass: Class<S>): S {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val unauthorizedInterceptor = UnauthorizedInterceptor(unauthorizedChallenge)
        val accessTokenInterceptor = AccessTokenInterceptor(authenticationDataSource)
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder
            .addInterceptor(unauthorizedInterceptor)
            .addInterceptor(accessTokenInterceptor)
        if (useLoginInterceptor) {
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}