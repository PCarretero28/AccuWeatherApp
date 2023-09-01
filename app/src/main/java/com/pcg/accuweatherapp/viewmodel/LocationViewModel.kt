package com.pcg.accuweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.repository.WeatherRepository

class LocationViewModel : ViewModel() {

    var repository: WeatherRepository = WeatherRepository()

    fun getLocationFromLiveData(geoPosition: String): LiveData<LocationModel> {
        Log.i("getLocationFromLiveData",geoPosition)
        return repository.getLocationDetailsFromAPI(geoPosition)
    }
}
