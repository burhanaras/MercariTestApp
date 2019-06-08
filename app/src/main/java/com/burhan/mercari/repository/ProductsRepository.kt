package com.burhan.mercari.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.burhan.mercari.database.ProductsDatabase
import com.burhan.mercari.database.asDomainModel
import com.burhan.mercari.domain.Product
import com.burhan.mercari.network.Network
import com.burhan.mercari.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Developed by tcbaras on 2019-06-09.
 */
class ProductsRepository(private val database: ProductsDatabase) {

    val products: LiveData<List<Product>> = Transformations.map(database.productDao.getAllProducts()){
        it.asDomainModel()
    }

    suspend fun refresh(){
        withContext(Dispatchers.IO){
            val downloadedProducts = Network.apiService.getAllProductsAsync().await()
            database.productDao.insertAll(*downloadedProducts.asDatabaseModel())
        }
    }
}