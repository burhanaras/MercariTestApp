package com.burhan.mercari.ui.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burhan.mercari.ui.productlist.ProductListViewModel

/**
 * Developed by tcbaras on 2019-06-08.
 */
class Factory(val app: Application, private val type: Int = 0): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(app, type) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}