package com.example.project.API

import com.squareup.moshi.Json

data class Restaurant(
    val address: String,
    val area: String,
    val city: String,
    val country: String,
    val id: Int,
    @Json(name = "image_url") val imageUrl: String?,
    val lat: Double,
    val lng: Double,
    val mobile_reserve_url: String,
    val name: String,
    val phone: String,
    val postal_code: String,
    val price: Int,
    val reserve_url: String,
    val state: String
)
