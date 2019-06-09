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

object Network {

    private const val TIMEOUT_IN_SECONDS: Long = 20
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)


}