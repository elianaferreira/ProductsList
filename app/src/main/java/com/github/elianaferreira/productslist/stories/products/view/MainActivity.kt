package com.github.elianaferreira.productslist.stories.products.view

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private var filterValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.includedToolbar.toolbar)
        binding.includedToolbar.toolbar.title = getString(R.string.products_label)
        setSupportActionBar(binding.includedToolbar.toolbar)

        binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)

        presenter = ProductsListPresenterImpl(this@MainActivity, this@MainActivity)
        presenter.loadList()

        binding.swProducts.setColorScheme(
            R.color.blue,
            R.color.dark_blue,
            R.color.blue)
        binding.swProducts.setOnRefreshListener {
            presenter.clearNoFavoriteList()
            presenter.loadList()
        }
    }

    override fun showList(products: List<Product>) {
        val curatedList = presenter.compareProducts(products)
        if (this@MainActivity::adapter.isInitialized) {
            //clear data, the adapter was previously loaded
            adapter.clearData()
        }
        adapter = ProductsListAdapter(this@MainActivity, curatedList, this@MainActivity)
        binding.rvProducts.adapter = adapter
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(show: Boolean) {
        binding.includedPb.progressbar.visibility = if (show) View.VISIBLE else View.GONE
        if (!show) binding.swProducts.isRefreshing = false
    }

    override fun onCheckedCallback(isChecked: Boolean, product: Product) {
        if (isChecked) {
            presenter.addProductToFavorite(product)
        } else {
            presenter.removeProductFromFavorite(product)
        }
    }

    /*
        When fail adding the product, it should be unchecked again and the list reloaded
     */
    override fun onAddProductFailed(product: Product) {
        adapter.updateProductState(product, false)
        showError(getString(R.string.add_fav_product_error_message))
    }

    override fun onRemoveProductFailed(product: Product) {
        adapter.updateProductState(product, true)
        showError(getString(R.string.remove_fav_product_error_message))
    }

    override fun reloadLitIfNeeded() {
        if (filterValue.isNotEmpty()) {
            adapter.filter.filter(filterValue)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (this@MainActivity::adapter.isInitialized) {
                    adapter.filter.filter(newText)
                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (!this@MainActivity::adapter.isInitialized) {
            return false
        }
        filterValue = when (item.itemId) {
            R.id.action_list_favorites -> {
                adapter.filter.filter(ProductsListAdapter.FavoriteFilter.FilterFavorite.name)
                ProductsListAdapter.FavoriteFilter.FilterFavorite.name
            }
            R.id.action_list_no_favorites -> {
                adapter.filter.filter(ProductsListAdapter.FavoriteFilter.FilterNoFavorite.name)
                ProductsListAdapter.FavoriteFilter.FilterNoFavorite.name
            }
            R.id.action_list_all -> {
                adapter.filter.filter("")
                ""
            }
            else -> {
                ""
            }
        }
        return super.onOptionsItemSelected(item)
    }

}