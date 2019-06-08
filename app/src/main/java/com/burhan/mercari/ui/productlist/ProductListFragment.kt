package com.burhan.mercari.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burhan.mercari.R
import com.burhan.mercari.databinding.FragmentProductsBinding
import com.burhan.mercari.domain.Product
import com.burhan.mercari.util.Factory

/**
 * A placeholder fragment containing a simple view.
 */
class ProductListFragment : Fragment() {

    private var productListAdapter: ProductListAdapter? = null

    private val viewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access ViewModel after onActivityCreated()"
        }

        ViewModelProviders.of(this, Factory(activity.application)).get(ProductListViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProductsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_products,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        productListAdapter = ProductListAdapter(ProductSelectCallback {
            // TODO: Got to ProductDetailActivity
            Toast.makeText(activity, "Product clicked: ${it.name} ", Toast.LENGTH_SHORT).show()
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            adapter = productListAdapter
            addItemDecoration(RecyclerViewItemDecoration(16))
        }
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.products.observe(viewLifecycleOwner, Observer<List<Product>> { products ->
            products?.apply {
                productListAdapter?.products = products
            }
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ProductListFragment {
            return ProductListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}