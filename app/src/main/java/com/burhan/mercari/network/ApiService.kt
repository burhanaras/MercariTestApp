package com.burhan.mercari.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * Developed by tcbaras on 2019-06-09.
 */
interface ApiService {

    @GET("all.json")
    fun getAllProductsAsync(): Deferred<List<NetworkProduct>>

    @GET("men.json")
    fun getMenProductsAsync(): Deferred<List<NetworkProduct>>

    @GET("women.json")
    fun getWomenProductsAsync(): Deferred<List<NetworkProduct>>
}
