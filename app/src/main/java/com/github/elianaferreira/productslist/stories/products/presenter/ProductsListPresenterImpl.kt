package com.github.elianaferreira.productslist.stories.products.presenter

import com.github.elianaferreira.productslist.stories.products.model.ProductsListRepository
import com.github.elianaferreira.productslist.stories.products.model.ProductsListRepositoryImpl
import com.github.elianaferreira.productslist.stories.products.model.entities.FavoriteResponse
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.stories.products.view.ProductsListView
import com.github.elianaferreira.productslist.utils.RequestCallback

class ProductsListPresenterImpl(private val view: ProductsListView):
        ProductsListPresenter,
        ProductsListRepository.OnProductsCallback {

    private val repository = ProductsListRepositoryImpl(this)

    override fun loadList() {
        view.showProgressBar(true)
        repository.getProductsList()
    }

    override fun addProductToFavorite(product: Product) {
        view.showProgressBar(true)
        repository.addFavorite(product)
    }

    override fun onGetProductsListSuccess(response: ProductsResponse) {
        view.showProgressBar(false)
        view.showList(response.list)
    }

    override fun onAddProductFavoriteSuccess(response: Product) {
        view.showProgressBar(false)
        view.onProductAdded(response)
    }

    override fun onError(errorMessage: String) {
        view.showProgressBar(false)
        view.showError(errorMessage)
    }
}