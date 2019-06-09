package com.burhan.mercari.network.service

import com.burhan.mercari.network.dto.NetworkProduct
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

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
