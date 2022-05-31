package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Badge(
    @SerializedName("badge_image_url")
    val badgeImageUrl: String,
    @SerializedName("badge_type")
    val badgeType: String
)