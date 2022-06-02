package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VendorInventory(
    @SerializedName("list_price")
    val listPrice: Double,
    @SerializedName("price")
    val price: Double
)