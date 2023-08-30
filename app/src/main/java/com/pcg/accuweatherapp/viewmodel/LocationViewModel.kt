package com.pcg.accuweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pcg.accuweatherapp.model.LocationModel
import com.pcg.accuweatherapp.repository.WeatherRepository

class LocationViewModel : ViewModel(){

    var repository: WeatherRepository = WeatherRepository()

    var locationLiveData: LiveData<LocationModel>

    init {
        locationLiveData = repository.getLocationDetailsFromAPI()
    }

    fun getLocationFromLiveData():LiveData<LocationModel>{
        return locationLiveData
    }


}