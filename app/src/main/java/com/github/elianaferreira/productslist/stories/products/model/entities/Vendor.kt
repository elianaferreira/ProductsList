package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Vendor(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String
)