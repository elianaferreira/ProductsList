package com.github.elianaferreira.productslist.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.elianaferreira.productslist.R
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.viewholder.GenericViewHolder

class ProductsListAdapter(private val context: Context, private val dataSet: List<Product>): RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return GenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val product = dataSet[position]
        holder.get(R.id.txt_item_description, TextView::class.java).text = product.name
        ImageLoaderWrapper.loadImage(
            holder.get(R.id.img_main, ImageView::class.java),
            product.mainImage)
        holder.get(R.id.txt_actual_price, TextView::class.java).text = context.getString(R.string.price_label, product.vendorInventory[0].listPrice.toString())
        holder.get(R.id.txt_previous_price, TextView::class.java).text = context.getString(R.string.price_label, product.vendorInventory[0].price.toString())
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}