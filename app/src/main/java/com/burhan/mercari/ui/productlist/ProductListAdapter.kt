package com.burhan.mercari.ui.productlist

/**
 * Developed by tcbaras on 2019-06-08.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.burhan.mercari.R
import com.burhan.mercari.databinding.ProductBinding
import com.burhan.mercari.domain.Product

class ProductListAdapter(private val callback: ProductSelectCallback) : RecyclerView.Adapter<ProductViewHolder>() {

    var products: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val withDataBinding: ProductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                ProductViewHolder.LAYOUT,
                parent,
                false
            )

        return ProductViewHolder(withDataBinding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.product = products[position]
            it.productCallback = callback
        }
    }
}


class ProductViewHolder(val viewDataBinding: ProductBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT: Int = R.layout.product
    }
}

class ProductSelectCallback(val block: (Product) -> Unit) {
    fun onSelect(product: Product) = block(product)
}