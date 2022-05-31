package com.github.elianaferreira.productslist.stories.products.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.elianaferreira.productslist.R
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.presenter.ProductsListPresenter
import com.github.elianaferreira.productslist.stories.products.presenter.ProductsListPresenterImpl

class MainActivity : AppCompatActivity(), ProductsListView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = ProductsListPresenterImpl(this@MainActivity)
        presenter.loadList()

    }

    override fun showList(products: List<Product>) {
        Log.d(">>>>>", "showList: $products")
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}