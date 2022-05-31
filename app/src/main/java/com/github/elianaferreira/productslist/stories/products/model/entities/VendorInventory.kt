package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VendorInventory(
    @SerializedName("has_promotions")
    val hasPromotions: Boolean,
    @SerializedName("is_published")
    val isPublished: Boolean,
    @SerializedName("list_price")
    val listPrice: Double,
    @SerializedName("marketplace_id")
    val marketplaceId: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("promotions")
    val promotions: List<Promotion>,
    @SerializedName("special_fee")
    val specialFee: String,
    @SerializedName("vendor")
    val vendor: Vendor,
    @SerializedName("vendor_inventory_id")
    val vendorInventoryId: String,
    @SerializedName("vendor_sku")
    val vendorSku: String
)