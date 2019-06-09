package com.burhan.mercari.ui.productlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burhan.mercari.database.getDatabase
import com.burhan.mercari.domain.Product
import com.burhan.mercari.repository.ProductsRepository
import kotlinx.coroutines.*

class ProductListViewModel(val app: Application, type: Int) : ViewModel() {

    private val viewModelJob: Job = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val database = getDatabase(app)
    private val repository = ProductsRepository(database)

    var productsToShow: LiveData<List<Product>> = MutableLiveData()

    init {
        viewModelScope.launch {
            repository.downloadAllProducts()
        }

        productsToShow = when (type) {
            0 -> repository.productsOfMen
            1 -> repository.allProducts
            else -> repository.productsOfWomen
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}