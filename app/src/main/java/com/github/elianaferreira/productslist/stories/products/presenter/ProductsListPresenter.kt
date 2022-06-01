package com.github.elianaferreira.productslist.stories.products.presenter

import com.github.elianaferreira.productslist.stories.products.model.entities.Product

interface ProductsListPresenter {
    fun loadList()
    fun addProductToFavorite(product: Product)
    fun removeProductFromFavorite(product: Product)
    fun saveOrRemoveProductFromNoFavorite(product: Product)
    fun compareProducts(products: List<Product>): List<Product>
}