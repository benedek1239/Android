package com.example.project.restaurantAPI

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

//Restaurant class
@Parcelize
data class Restaurant(
    val address: String,
    val area: String,
    val city: String,
    val country: String,
    val id: Int,
    @Json(name = "image_url") val imageUrl: String?,
    val lat: String,
    val lng: String,
    val mobile_reserve_url: String,
    val name: String,
    val phone: String,
    val postal_code: String,
    val price: Int,
    val reserve_url: String,
    val state: String
) : Parcelable
