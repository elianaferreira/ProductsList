package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PromotionProperties(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("get")
    val `get`: Int
)