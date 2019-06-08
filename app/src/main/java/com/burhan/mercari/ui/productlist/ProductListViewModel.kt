package com.burhan.mercari.ui.productlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.burhan.mercari.database.getDatabase
import com.burhan.mercari.domain.Product
import com.burhan.mercari.repository.ProductsRepository
import kotlinx.coroutines.*

class ProductListViewModel(val app: Application) : ViewModel() {

    private val viewModelJob: Job = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val database = getDatabase(app)
    private val repository = ProductsRepository(database)


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        products.value = dummyData()
        viewModelScope.launch {
            repository.refresh()
        }
    }

    val productsToShow = repository.products

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun dummyData(): List<Product> {
        var product0 = Product(
            "0",
            "Product0",
            "on_sale",
            "10",
            "10",
            "$ 50.0",
            "https://images.yourstory.com/cs/wordpress/2016/08/125-fall-in-love.png"
        )
        var product1 = Product(
            "0",
            "Product1",
            "sold_out",
            "10",
            "10",
            "$ 50.0",
            "https://dummyimage.com/400x400/000/fff?text=women2"
        )
        var product2 = Product(
            "0",
            "Product1",
            "sold_out",
            "10",
            "10",
            "$ 50.0",
            "https://dummyimage.com/400x400/000/fff?text=women2"
        )
        var product3 = Product(
            "0",
            "Product1",
            "sold_out",
            "10",
            "10",
            "$ 50.0",
            "https://dummyimage.com/400x400/000/fff?text=women2"
        )
        return listOf(product0, product1, product2, product3)
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}