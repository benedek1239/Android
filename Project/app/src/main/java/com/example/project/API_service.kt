package com.example.project

import com.example.project.restaurantAPI.Restaurants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//Éttermek API adatbázisának lekérése

private const val BASE_URL = "https://ratpark-api.imok.space/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface RestaurantsApiService{
    @GET("restaurants")
    fun getProperties():
            Deferred<Restaurants>
}

object RestaurantsApi{
    val retrofitService : RestaurantsApiService by lazy {
        retrofit.create(RestaurantsApiService::class.java)
    }
}

