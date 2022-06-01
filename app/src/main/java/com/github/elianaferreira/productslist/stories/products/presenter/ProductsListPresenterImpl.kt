package com.github.elianaferreira.productslist.stories.products.presenter

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.github.elianaferreira.productslist.stories.products.model.ProductsListRepository
import com.github.elianaferreira.productslist.stories.products.model.ProductsListRepositoryImpl
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.stories.products.view.ProductsListView

class ProductsListPresenterImpl(
        private val context: Context,
        private val view: ProductsListView):
        ProductsListPresenter,
        ProductsListRepository.OnProductsCallback {

    private val repository = ProductsListRepositoryImpl(context, this)

    override fun loadList() {
        view.showProgressBar(true)
        repository.getProductsList()
    }

    override fun addProductToFavorite(product: Product) {
        view.showProgressBar(true)
        repository.addFavorite(product)
    }

    override fun removeProductFromFavorite(product: Product) {
        view.showProgressBar(true)
        repository.removeFavorite(product)
    }

    override fun onGetProductsListSuccess(response: ProductsResponse) {
        view.showProgressBar(false)
        view.showList(response.list)
    }

    override fun onAddProductFavoriteSuccess(response: Product) {
        view.showProgressBar(false)
        response.isFavouriteProduct = true
        saveOrRemoveProductFromNoFavorite(response)
    }

    override fun onRemoveProductFavoriteSuccess(response: Product) {
        view.showProgressBar(false)
        response.isFavouriteProduct = false
        saveOrRemoveProductFromNoFavorite(response)
    }

    override fun onError(errorMessage: String) {
        view.showProgressBar(false)
        view.showError(errorMessage)
    }

    override fun onAddProductFavoriteFailure(response: Product) {
        view.showProgressBar(false)
        view.onAddProductFailed(response)
    }

    override fun onRemoveProductFavoriteFailure(response: Product) {
        view.showProgressBar(false)
        view.onRemoveProductFailed(response)
    }

    /**
     * All products are favorites by default.
     * This method add products that were unchecked to a Nofav list.
     */
    override fun saveOrRemoveProductFromNoFavorite(product: Product) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (product.isFavouriteProduct) {
            prefs.edit().remove(product.id).apply()
        } else {
            prefs.edit().putBoolean(product.id, true).apply()
        }
        view.reloadLitIfNeeded()
    }

    override fun compareProducts(products: List<Product>): List<Product> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        for (product in products) {
            //if the product is in th NoFav list, change the attribute to false
            product.isFavouriteProduct = !prefs.contains(product.id)
        }
        return products
    }

    override fun clearNoFavoriteList() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .clear()
            .apply()
    }
}