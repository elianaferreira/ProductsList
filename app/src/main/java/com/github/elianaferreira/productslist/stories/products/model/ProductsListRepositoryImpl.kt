package com.github.elianaferreira.productslist.stories.products.model

import android.util.Log
import com.github.elianaferreira.productslist.BuildConfig
import retrofit2.Callback
import com.github.elianaferreira.productslist.service.ApiInterface
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.utils.RequestCallback
import retrofit2.Call
import retrofit2.Response

class ProductsListRepositoryImpl(val requestCallback: RequestCallback): ProductsListRepository {

    private val TAG = ProductsListRepository::class.simpleName!!

    override fun getProductsList() {
        val apiInterface = ApiInterface.create().getProducts()
        apiInterface.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if(response.body() != null) {
                    requestCallback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                if (BuildConfig.DEBUG) Log.e(TAG, "failure getting products", t)
                requestCallback.onError(t.message!!)
            }
        })
    }
}