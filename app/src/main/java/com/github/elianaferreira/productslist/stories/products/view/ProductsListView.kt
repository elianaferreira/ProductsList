package com.github.elianaferreira.productslist.stories.products.view

import com.github.elianaferreira.productslist.stories.products.model.entities.Product

interface ProductsListView {
    fun showList(products: List<Product>)
    fun showError(message: String)
    fun showProgressBar(show: Boolean)
    fun onAddProductFailed(product: Product)
    fun onRemoveProductFailed(product: Product)
    fun reloadLitIfNeeded()
}