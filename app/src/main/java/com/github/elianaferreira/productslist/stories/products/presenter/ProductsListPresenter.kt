package com.github.elianaferreira.productslist.stories.products.presenter

import com.github.elianaferreira.productslist.stories.products.model.Product

interface ProductsListPresenter {
    fun loadList(): List<Product>
}