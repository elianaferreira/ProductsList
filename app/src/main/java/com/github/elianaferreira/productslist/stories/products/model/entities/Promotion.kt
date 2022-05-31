package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Promotion(
    @SerializedName("effective_price")
    val effectivePrice: Double,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("promotion_notes")
    val promotionNotes: String,
    @SerializedName("promotion_properties")
    val promotionProperties: PromotionProperties,
    @SerializedName("promotion_type")
    val promotionType: String,
    @SerializedName("start_date")
    val startDate: String
)