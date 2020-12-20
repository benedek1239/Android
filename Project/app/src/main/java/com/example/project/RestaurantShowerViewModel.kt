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

    //Splash screen eltüntetéséhez használt változó
    var restaurantsReady : MutableLiveData<Boolean> = MutableLiveData()

    //A belső MutableLiveData, amely a legfrissebb kérés állapotát tárolja
    private val _status = MutableLiveData<RestaurantsApiStatus>()

    val status: LiveData<RestaurantsApiStatus>
        get() = _status

    //Belsőleg MutableLiveData-t használunk, mert frissíteni fogjuk a Restaurant listáját új értékekkel
    private val _properties = MutableLiveData<List<Restaurant>>()

    val properties: LiveData<List<Restaurant>>
        get() = _properties

    //A navigáláshoz tároljuk a [Restaurant]-ot, amelyre navigálni akarunk
    private val _navigateToSelectedProperty = MutableLiveData<Restaurant>()
    val navigateToSelectedProperty : LiveData<Restaurant>
        get() = _navigateToSelectedProperty

    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    //Meghíváskor lekéri az éttermeket
    init {
        getRestaurantsProperties()
    }

    //Éttermek lekérése az API-ból
    //status firssítése, az éttermek lekérésének állapotáról
    public fun getRestaurantsProperties() {
        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
                val mainRestaurantsList = getInformation.await()

                _status.value = RestaurantsApiStatus.DONE

                //Splash screen figyelője ebből tudja, hogy betöltöttük az éttermeket
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

    //Amikor az étteremre klikkelünk átállitsa a [_navigateToSelectedProperty] [MutableLiveData] paraméterét a klicked [Restaurant]-ra
    fun displayPropertyDetails(restaurantproperty: Restaurant){
        _navigateToSelectedProperty.value = restaurantproperty
    }

    //A navigálás után  biztosan null-ra állitsa a navigateToSelectedProperty - t
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }
}