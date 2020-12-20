package com.example.project.restaurantAPI

//Restaurants class
data class Restaurants(
    val page: Int,
    val per_page: Int,
    val restaurants: List<Restaurant>,
    val total_entries: Int
)