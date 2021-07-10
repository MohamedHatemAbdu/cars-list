package com.me.data.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object ServiceGenerator {

    private const val BASE_URL = "https://cdn.sixt.io/"


    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())


    private var retrofit = builder.build()


    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(
        serviceClass: Class<S>?
        , debug: Boolean
    ): S {
        if (!httpClient.interceptors().contains(logging) && debug) {
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit.create(serviceClass)
    }
}