package com.example.project.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.restaurantAPI.Restaurant

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [MarsProperty].
 */
class DetailViewModel(restaurantProperty: Restaurant, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<Restaurant>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<Restaurant>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = restaurantProperty
    }

}