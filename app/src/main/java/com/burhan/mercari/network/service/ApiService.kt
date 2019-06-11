package com.burhan.mercari.network.service

import com.burhan.mercari.network.dto.CategoryUrlHolder
import com.burhan.mercari.network.dto.NetworkProduct
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Developed by tcbaras on 2019-06-09.
 */
interface ApiService {

    @GET("master.json")
    fun getUrlsForCategoriesAsync(): Deferred<List<CategoryUrlHolder>>

    @GET
    fun getAllProductsAsync(@Url url: String): Deferred<List<NetworkProduct>>

    @GET
    fun getMenProductsAsync(@Url url: String): Deferred<List<NetworkProduct>>

    @GET
    fun getWomenProductsAsync(@Url url: String): Deferred<List<NetworkProduct>>

}
