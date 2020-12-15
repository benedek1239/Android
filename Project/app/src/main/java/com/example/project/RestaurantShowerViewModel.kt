package com.example.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.API.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantShowerViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<Restaurant>>()

    val properties: LiveData<List<Restaurant>>
        get() = _properties


    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    init {
        getRestaurantsProperties()
    }

    private fun getRestaurantsProperties() {
        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
                val mainRestaurantsList = getInformation.await()

                if (mainRestaurantsList.restaurants.size > 0){
                    _properties.value = mainRestaurantsList.restaurants
                }
            }catch (t: Exception){
                _status.value = "Failure: "+ t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}