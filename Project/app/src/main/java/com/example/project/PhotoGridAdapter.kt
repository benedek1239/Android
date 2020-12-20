package com.example.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.restaurantAPI.Restaurant
import com.example.project.databinding.RestaurantViewBinding

class PhotoGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Restaurant, PhotoGridAdapter.RestaurantPropertyViewHolder>(DiffCallback) {

    //konstruktor átteszi a binding változót a GridViewItem-ből, amely hozzáférést biztosít a teljes [Restaurant] információkhoz.
    class RestaurantPropertyViewHolder(private var binding: RestaurantViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurantProperty: Restaurant) {
            binding.property = restaurantProperty
            binding.executePendingBindings()
        }
    }

    //Megengedi a recycleview nek, hogy meghatározza, mely elemek változtak, amikor a [Rextaurant] [List]-je firssitve lett
    companion object DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }
    }

    //Létrehoz új [RecyclerView] elem nézeteket
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantPropertyViewHolder {
        return RestaurantPropertyViewHolder(RestaurantViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    //A view tartalmás kicseréli a választott étteremre
    override fun onBindViewHolder(holder: RestaurantPropertyViewHolder, position: Int) {
        val restaurantProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(restaurantProperty)
        }
        holder.bind(restaurantProperty)
    }


    //Listener, amely kezeli a [RecyclerView] elemekre történő kattintásokat. Küldi a  [Restaurant]-ot az aktuális elemhez társítva az [onClick] függvényhez.
    class OnClickListener(val clickListener: (restaurantProperty:Restaurant) -> Unit) {
        fun onClick(restaurantProperty:Restaurant) = clickListener(restaurantProperty)
    }


}
