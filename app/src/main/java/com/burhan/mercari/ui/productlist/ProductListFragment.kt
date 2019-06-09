package com.burhan.mercari.ui.productlist

import android.content.res.Configuration
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
import com.burhan.mercari.ui.core.RecyclerViewItemDecoration
import com.burhan.mercari.ui.core.Factory

/**
 * A placeholder fragment containing a simple view.
 */
class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access ViewModel after onActivityCreated()"
        }

        val type = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        ViewModelProviders.of(this, Factory(activity.application, type)).get(ProductListViewModel::class.java)
    }

    private var productListAdapter: ProductListAdapter? = null
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)

        addClickListeners()

        initUI()

        return binding.root

    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_products,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun addClickListeners() {
        productListAdapter = ProductListAdapter(ProductSelectCallback {
            // TODO: Go to ProductDetailActivity
            Toast.makeText(activity, "Product clicked: ${it.name} ", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initUI() {
        val gridLayoutManager =
            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(context, 2)
            } else {
                GridLayoutManager(context, 4)
            }

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = gridLayoutManager
            adapter = productListAdapter
            addItemDecoration(RecyclerViewItemDecoration(16))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.productsToShow.observe(viewLifecycleOwner, Observer<List<Product>> { products ->
            products?.apply {
                productListAdapter?.products = products
            }
        })
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

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