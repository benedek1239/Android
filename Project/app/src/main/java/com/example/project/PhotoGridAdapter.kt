package com.example.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.API.Restaurant
import com.example.project.databinding.RestaurantViewBinding

class PhotoGridAdapter :
    ListAdapter<Restaurant, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    class MarsPropertyViewHolder(private var binding: RestaurantViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurantProperty: Restaurant) {
            binding.property = restaurantProperty
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(RestaurantViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val restaurantProperty = getItem(position)
        holder.bind(restaurantProperty)
    }


    /**
    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
    val restaurantProperty = getItem(position)
    holder.itemView.setOnClickListener {
    onClickListener.onClick(restaurantProperty)
    }
    holder.bind(restaurantProperty)
    }


    class OnClickListener(val clickListener: (restaurantProperty:Restaurant) -> Unit) {
    fun onClick(restaurantProperty:Restaurant) = clickListener(restaurantProperty)
    }
     */

}
