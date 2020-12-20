package com.example.project.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.restaurantAPI.Restaurant

//Információkat tartalmaz a kiválasztott étteremről
class DetailViewModel(restaurantProperty: Restaurant, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<Restaurant>()

    //A külső LiveData
    val selectedProperty: LiveData<Restaurant>
        get() = _selectedProperty

    //A választott étterem inicializálása
    init {
        _selectedProperty.value = restaurantProperty
    }

}