package com.pcg.accuweatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pcg.accuweatherapp.model.LocationModel
import com.pcg.accuweatherapp.retrofit.AccuWeatherAPI
import com.pcg.accuweatherapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepository {

    var accuWeatherAPI : AccuWeatherAPI

    init {
        accuWeatherAPI = RetrofitInstance().getRetrofitInstance()
            .create(AccuWeatherAPI::class.java)
    }

    fun getLocationDetailsFromAPI():LiveData<LocationModel>{

        //LiveData
        var data = MutableLiveData<LocationModel>()

        var location:LocationModel

        GlobalScope.launch(Dispatchers.IO) {

            //Returning the Response<LocationModel>
            val response = accuWeatherAPI.getLocationDetails()

            if(response != null){
                //saving the data to list
                location = response.body()!!

                data.postValue(location)
                Log.i("TAGY", "${data.value}")
            }
        }
        return data
    }
}