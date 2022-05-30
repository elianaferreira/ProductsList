package com.github.elianaferreira.productslist.stories.products.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Product(
    @SerializedName("advertising_badges")
    val advertisingBadges: AdvertisingBadges,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("is_favourite_product")
    val isFavouriteProduct: Boolean,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("main_image_240_box")
    val mainImage240Box: Any,
    @SerializedName("main_image_240_wide")
    val mainImage240Wide: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit_name")
    val unitName: String,
    @SerializedName("vendor_inventory")
    val vendorInventory: List<VendorInventory>
)