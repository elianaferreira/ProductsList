package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("advertising_badges")
    val advertisingBadges: AdvertisingBadges,
    @SerializedName("is_favourite_product")
    var isFavouriteProduct: Boolean,
    @SerializedName("main_image")
    val mainImage: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("vendor_inventory")
    val vendorInventory: List<VendorInventory>
)