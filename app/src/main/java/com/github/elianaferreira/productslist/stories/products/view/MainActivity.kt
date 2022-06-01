package com.github.elianaferreira.productslist.stories.products.view

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.elianaferreira.productslist.R
import com.github.elianaferreira.productslist.databinding.ActivityMainBinding
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.presenter.ProductsListPresenter
import com.github.elianaferreira.productslist.stories.products.presenter.ProductsListPresenterImpl
import com.github.elianaferreira.productslist.utils.ProductsListAdapter

class MainActivity : AppCompatActivity(), ProductsListView, ProductsListAdapter.CheckboxCallback {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductsListAdapter
    private lateinit var presenter: ProductsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.includedToolbar.toolbar)
        binding.includedToolbar.toolbarTitle.text = getString(R.string.products_label)
        setSupportActionBar(binding.includedToolbar.toolbar)

        binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)

        presenter = ProductsListPresenterImpl(this@MainActivity)
        presenter.loadList()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.svProducts.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        binding.svProducts.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (this@MainActivity::adapter.isInitialized) {
                    adapter.filter.filter(newText)
                }
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun showList(products: List<Product>) {
        adapter = ProductsListAdapter(this@MainActivity, products, this@MainActivity)
        binding.rvProducts.adapter = adapter
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(show: Boolean) {
        binding.includedPb.progressbar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onCheckedCallback(isChecked: Boolean, product: Product) {
        if (isChecked) {
            presenter.addProductToFavorite(product)
        }
    }

    override fun onProductAdded(product: Product) {
        //TODO save somewhere
    }
}