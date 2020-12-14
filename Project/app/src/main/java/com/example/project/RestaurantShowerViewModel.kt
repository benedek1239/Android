package com.example.project

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


    private val _resInfo = MutableLiveData<List<Restaurant>>()
    val resInfo: LiveData<List<Restaurant>>
        get() = _resInfo



    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    init {
        getRestaurantsProperties()
    }

    private fun getRestaurantsProperties() {
        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
                val listResult = getInformation.await()

                if (listResult.restaurants.isNotEmpty()){
                    _resInfo.value = listResult.restaurants
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