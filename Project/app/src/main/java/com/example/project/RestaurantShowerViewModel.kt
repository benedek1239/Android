package com.example.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.restaurantAPI.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class RestaurantsApiStatus { ERROR, DONE }

class RestaurantShowerViewModel : ViewModel() {

    var restaurantsReady : MutableLiveData<Boolean> = MutableLiveData()

    private val _status = MutableLiveData<RestaurantsApiStatus>()

    val status: LiveData<RestaurantsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Restaurant>>()

    val properties: LiveData<List<Restaurant>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<Restaurant>()
    val navigateToSelectedProperty : LiveData<Restaurant>
        get() = _navigateToSelectedProperty

    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    init {
        getRestaurantsProperties()
    }

    public fun getRestaurantsProperties() {
        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
                val mainRestaurantsList = getInformation.await()

                _status.value = RestaurantsApiStatus.DONE

                restaurantsReady.postValue(true);

                if (mainRestaurantsList.restaurants.size > 0){
                    _properties.value = mainRestaurantsList.restaurants
                }
            }catch (t: Exception){
                _status.value = RestaurantsApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun displayPropertyDetails(restaurantproperty: Restaurant){
        _navigateToSelectedProperty.value = restaurantproperty
    }

    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }
}