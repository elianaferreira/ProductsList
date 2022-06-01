package com.github.elianaferreira.productslist.utils

import android.widget.ImageView
import com.github.elianaferreira.productslist.R
import com.squareup.picasso.Picasso

class ImageLoaderWrapper {

    companion object {
        fun loadImage(imageView: ImageView, url: String?) {
            if (url != null) {
                Picasso
                    .get()
                    .load(url)
                    .error(R.drawable.image)
                    .placeholder(R.drawable.image)
                    .into(imageView)
            } else {
                Picasso
                    .get()
                    .load(R.drawable.image)
                    .into(imageView)
            }
        }
    }
}