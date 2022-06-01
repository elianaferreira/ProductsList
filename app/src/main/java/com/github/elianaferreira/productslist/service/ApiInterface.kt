package com.github.elianaferreira.productslist.service

import com.github.elianaferreira.productslist.BuildConfig
import com.github.elianaferreira.productslist.stories.products.model.entities.ProductsResponse
import com.github.elianaferreira.productslist.stories.products.model.entities.FavoriteResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            var httpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                httpClient.addInterceptor(logInterceptor)
            }

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
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