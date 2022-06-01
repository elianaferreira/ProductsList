package com.github.elianaferreira.productslist.stories.products.model

import android.content.Context
import android.util.Log
import com.github.elianaferreira.productslist.BuildConfig
import com.github.elianaferreira.productslist.R
import retrofit2.Callback
import com.github.elianaferreira.productslist.service.ApiInterface
import com.github.elianaferreira.productslist.stories.products.model.entities.FavoriteResponse
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import retrofit2.Call
import retrofit2.Response

class ProductsListRepositoryImpl(
    private val context: Context,
    private val requestCallback: ProductsListRepository.OnProductsCallback): ProductsListRepository {

    private val TAG = ProductsListRepository::class.simpleName!!

    override fun getProductsList() {
        val apiInterface = ApiInterface.create().getProducts()
        apiInterface.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if(response.body() != null) {
                    requestCallback.onGetProductsListSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                if (BuildConfig.DEBUG) Log.e(TAG, "failure getting products", t)
                requestCallback.onError(context.getString(R.string.generic_error_message))
            }
        })
    }

    override fun addFavorite(product: Product) {
        val apiInterface = ApiInterface.create().addProductToFavorites()
        apiInterface.enqueue(object : Callback<FavoriteResponse> {
            override fun onResponse(call: Call<FavoriteResponse>, response: Response<FavoriteResponse>) {
                if(response.body() != null) {
                    requestCallback.onAddProductFavoriteSuccess(product)
                } else {
                    requestCallback.onAddProductFavoriteFailure(product)
                }
            }

            override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                if (BuildConfig.DEBUG) Log.e(TAG, "failure adding product", t)
                requestCallback.onAddProductFavoriteFailure(product)
            }
        })
    }

    override fun removeFavorite(product: Product) {
        val apiInterface = ApiInterface.create().removeProductFromFavorites()
        apiInterface.enqueue(object : Callback<FavoriteResponse> {
            override fun onResponse(call: Call<FavoriteResponse>, response: Response<FavoriteResponse>) {
                if(response.body() != null) {
                    requestCallback.onRemoveProductFavoriteSuccess(product)
                } else {
                    requestCallback.onRemoveProductFavoriteFailure(product)
                }
            }

            override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                if (BuildConfig.DEBUG) Log.e(TAG, "failure removing product", t)
                requestCallback.onError(context.getString(R.string.remove_fav_product_error_message))
            }
        })
    }
}