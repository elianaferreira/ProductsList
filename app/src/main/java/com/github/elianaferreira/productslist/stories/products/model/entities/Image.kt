package com.github.elianaferreira.productslist.stories.products.model.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Image(
    @SerializedName("image")
    val image: String,
    @SerializedName("image_240_box")
    val image240Box: String,
    @SerializedName("image_240_wide")
    val image240Wide: String,
    @SerializedName("main_image_bool")
    val mainImageBool: Boolean
)