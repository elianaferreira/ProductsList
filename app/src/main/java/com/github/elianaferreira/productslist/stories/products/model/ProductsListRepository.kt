package com.github.elianaferreira.productslist.stories.products.model

import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.utils.RequestCallback

interface ProductsListRepository {
    fun getProductsList()
    fun addFavorite(product: Product)
    fun removeFavorite(product: Product)

    interface OnProductsCallback: RequestCallback {
        fun onGetProductsListSuccess(response: ProductsResponse)
        fun onAddProductFavoriteSuccess(response: Product)
        fun onRemoveProductFavoriteSuccess(response: Product)
    }
}