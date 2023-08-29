package com.pcg.accuweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcg.accuweatherapp.model.LocationModel

class LocationViewModel : ViewModel(){

    val locationModel = MutableLiveData<LocationModel>()

    fun getLocation(){

    }

}