package com.github.elianaferreira.productslist.stories.products.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.elianaferreira.productslist.R
import com.github.elianaferreira.productslist.databinding.ActivityMainBinding
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.presenter.ProductsListPresenterImpl
import com.github.elianaferreira.productslist.utils.ProductsListAdapter

class MainActivity : AppCompatActivity(), ProductsListView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.includedToolbar.toolbar)
        binding.includedToolbar.toolbarTitle.text = getString(R.string.products_label)
        setSupportActionBar(binding.includedToolbar.toolbar)

        binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)

        val presenter = ProductsListPresenterImpl(this@MainActivity)
        presenter.loadList()
    }

    override fun showList(products: List<Product>) {
        binding.rvProducts.adapter = ProductsListAdapter(this@MainActivity, products)
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(show: Boolean) {
        binding.includedPb.progressbar.visibility = if (show) View.VISIBLE else View.GONE
    }
}