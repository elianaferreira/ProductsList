package com.github.elianaferreira.productslist.stories.products.model.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Response(
    @SerializedName("favorite")
    val favorite: Boolean)