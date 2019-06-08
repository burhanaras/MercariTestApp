package com.burhan.mercari.ui.productlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burhan.mercari.domain.Product

class ProductListViewModel(val app: Application) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        products.value  = dummyData()
    }

    private fun dummyData(): List<Product> {
        var product0 = Product("0","Product0","Status",10,10,50.0,"https://images.yourstory.com/cs/wordpress/2016/08/125-fall-in-love.png")
        var product1 = Product("0","Product1","Status",10,10,50.0,"https://dummyimage.com/400x400/000/fff?text=women2")
        return listOf(product0, product1)
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}