package com.github.elianaferreira.productslist.stories.products.presenter

import com.github.elianaferreira.productslist.stories.products.model.ProductsListRepositoryImpl
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.stories.products.view.ProductsListView
import com.github.elianaferreira.productslist.utils.RequestCallback

class ProductsListPresenterImpl(private val view: ProductsListView): ProductsListPresenter, RequestCallback {

    private val repository = ProductsListRepositoryImpl(this)

    override fun loadList() {
        view.showProgressBar(true)
        repository.getProductsList()
    }

    override fun onSuccess(response: Any) {
        view.showProgressBar(false)
        view.showList((response as ProductsResponse).list)
    }

    override fun onError(errorMessage: String) {
        view.showProgressBar(false)
        view.showError(errorMessage)
    }
}