package com.example.project

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.restaurantAPI.Restaurant


//Amikor nincs adat a Restaurant-ba (data is null) akkor elrejti, hanem megmutatja
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Restaurant>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


//Glide könyvtár segítségével megmutatja a kép URL-t az imageview ba
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

