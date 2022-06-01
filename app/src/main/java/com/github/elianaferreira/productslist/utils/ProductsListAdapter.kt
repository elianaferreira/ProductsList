package com.github.elianaferreira.productslist.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.github.elianaferreira.productslist.R
import com.github.elianaferreira.productslist.stories.products.model.entities.Product
import com.github.elianaferreira.viewholder.GenericViewHolder

class ProductsListAdapter(private val context: Context,
                          private val dataSet: List<Product>,
                          private val callback: CheckboxCallback):
    RecyclerView.Adapter<GenericViewHolder>(), Filterable {

    enum class FavoriteFilter {
        FilterFavorite,
        FilterNoFavorite
    }

    private var filteredDataSet = dataSet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return GenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val product = filteredDataSet[position]
        holder.get(R.id.txt_item_description, TextView::class.java).text = product.name
        ImageLoaderWrapper.loadImage(
            holder.get(R.id.img_main, ImageView::class.java),
            product.mainImage)
        holder.get(R.id.txt_actual_price, TextView::class.java).text = context.getString(R.string.price_label, product.vendorInventory[0].listPrice.toString())
        holder.get(R.id.txt_previous_price, TextView::class.java).text = context.getString(R.string.price_label, product.vendorInventory[0].price.toString())
        holder.get(R.id.chk_fav, CheckBox::class.java).isChecked = product.isFavouriteProduct
        holder.get(R.id.chk_fav, CheckBox::class.java).setOnCheckedChangeListener {_, isChecked ->
            product.isFavouriteProduct = isChecked
            callback.onCheckedCallback(isChecked, product)
        }

        holder.get(R.id.badge, TextView::class.java).visibility =
            if (product.advertisingBadges.badges != null && product.advertisingBadges.badges.first().badgeImageUrl != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    override fun getItemCount(): Int {
        return filteredDataSet.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val queryString = query.toString()
                filteredDataSet = if (queryString.isEmpty()) {
                    dataSet
                } else {
                    when (queryString) {
                        FavoriteFilter.FilterFavorite.name -> {
                            dataSet.filter { it.isFavouriteProduct }
                        }
                        FavoriteFilter.FilterNoFavorite.name -> {
                            dataSet.filter { !it.isFavouriteProduct }
                        }
                        else -> {
                            dataSet.filter { it.name.lowercase().contains(queryString.lowercase()) }
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredDataSet
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredDataSet = p1?.values as List<Product>
                notifyDataSetChanged()
            }

        }
    }

    interface CheckboxCallback {
        fun onCheckedCallback(isChecked: Boolean, product: Product)
    }

    fun clearData() {
        filteredDataSet = ArrayList<Product>()
        notifyDataSetChanged()
    }

    fun updateProductState(product: Product, favoriteState: Boolean) {
        filteredDataSet[filteredDataSet.indexOf(product)].isFavouriteProduct = favoriteState
        notifyDataSetChanged()
    }
}