package com.example.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.restaurantAPI.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

class RestaurantShowerViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
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

                _status.value = MarsApiStatus.DONE

                if (mainRestaurantsList.restaurants.size > 0){
                    _properties.value = mainRestaurantsList.restaurants
                }
            }catch (t: Exception){
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}