package com.pcg.accuweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pcg.accuweatherapp.model.currentweather.CurrentWeatherModel
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.repository.WeatherRepository

class LocationViewModel : ViewModel() {

    var repository: WeatherRepository = WeatherRepository()

    fun getLocationFromLiveData(geoPosition: String): LiveData<LocationModel> {
        return repository.getLocationDetailsFromAPI(geoPosition)
    }

    fun getCurrentWeatherFromKey(id: String) : LiveData<List<CurrentWeatherModel>>{
        return repository.getCurrentWeatherFromAPI(id)
    }
}
