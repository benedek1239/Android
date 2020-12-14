package com.example.project.API

data class Restaurants(
    val page: Int,
    val per_page: Int,
    val restaurants: List<Restaurant>,
    val total_entries: Int
)