package com.github.elianaferreira.productslist.service

import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.stories.products.model.entities.FavoriteResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    companion object {
        private const val BASE_URL = "https://demo5514996.mockable.io"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

    @GET("/products")
    fun getProducts(): Call<ProductsResponse>

    @POST("/favorites")
    fun addProductToFavorites(): Call<FavoriteResponse>

    @DELETE("/favorites")
    fun removeProductFromFavorites(): Call<FavoriteResponse>
}