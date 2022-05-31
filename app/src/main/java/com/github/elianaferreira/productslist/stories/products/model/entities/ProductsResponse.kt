package com.github.elianaferreira.productslist.stories.products.model.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductsResponse(
    @SerializedName("hits")
    val list: List<Product>
)