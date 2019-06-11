package com.burhan.mercari.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.burhan.mercari.database.ProductsDatabase
import com.burhan.mercari.database.entity.asDomainModel
import com.burhan.mercari.domain.Product
import com.burhan.mercari.network.Network
import com.burhan.mercari.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Developed by tcbaras on 2019-06-09.
 */
class ProductsRepository(private val database: ProductsDatabase) {

    // Since those url's are dynamic, we keep them in variables. They are retrieved from webservice.
    private var allProductsServiceUrl: String? = null
    private var menProductsServiceUrl: String? = null
    private var womenProductsServiceUrl: String? = null

    val allProducts: LiveData<List<Product>> = Transformations.map(database.productDao.getAllProducts()) {
        it.asDomainModel()
    }

    val productsOfMen: LiveData<List<Product>> = Transformations.map(database.productDao.getAllProductsOfMen()) {
        it.asDomainModel()
    }

    val productsOfWomen: LiveData<List<Product>> = Transformations.map(database.productDao.getAllProductsOfWomen()) {
        it.asDomainModel()
    }

    suspend fun downloadCategoryUrls() {
        withContext(Dispatchers.IO) {
            try {
                val categoryUrls = Network.apiService.getUrlsForCategoriesAsync().await()

                allProductsServiceUrl = categoryUrls.firstOrNull { it.name == CATEGORY_ALL }?.data
                menProductsServiceUrl = categoryUrls.firstOrNull { it.name == CATEGORY_MEN }?.data
                womenProductsServiceUrl = categoryUrls.firstOrNull { it.name == CATEGORY_WOMEN }?.data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun downloadAllProducts() {
        withContext(Dispatchers.IO) {
            try {
                if (allProductsServiceUrl == null) {
                    downloadCategoryUrls()
                }
                val downloadedProducts = Network.apiService.getAllProductsAsync(allProductsServiceUrl!!).await()
                database.productDao.insertAll(*downloadedProducts.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun downloadProductsOfMen() {
        withContext(Dispatchers.IO) {
            try {
                val downloadedProducts = Network.apiService.getMenProductsAsync("").await()
                database.productDao.insertAll(*downloadedProducts.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun downloadProductsOfWomen() {
        withContext(Dispatchers.IO) {
            try {
                val downloadedProducts = Network.apiService.getWomenProductsAsync("").await()
                database.productDao.insertAll(*downloadedProducts.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val CATEGORY_ALL = "All"
        const val CATEGORY_MEN = "Men"
        const val CATEGORY_WOMEN = "Women"
    }
}