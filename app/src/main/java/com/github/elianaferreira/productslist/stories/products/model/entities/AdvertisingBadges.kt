package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AdvertisingBadges(
    @SerializedName("badges")
    val badges: List<Badge>?,
    @SerializedName("has_badge")
    val hasBadge: Boolean
)