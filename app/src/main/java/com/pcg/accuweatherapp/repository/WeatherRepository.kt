package com.pcg.accuweatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.retrofit.AccuWeatherAPI
import com.pcg.accuweatherapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepository {

    var accuWeatherAPI: AccuWeatherAPI

    init {
        accuWeatherAPI = RetrofitInstance().getRetrofitInstance()
            .create(AccuWeatherAPI::class.java)
    }

    fun getLocationDetailsFromAPI(geoPosition: String): LiveData<LocationModel> {
        // LiveData
        val data = MutableLiveData<LocationModel>()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Devuelve Response<LocationModel>
                val response = accuWeatherAPI.getLocationDetails(geoPosition)
                Log.i("getLocationDetailsFromAPI", response.toString())

                if (response.isSuccessful) {
                    // Guarda los datos en la lista
                    val location = response.body()

                    if (location != null) {
                        data.postValue(location!!)
                        Log.i("TAGY", "${data.value}")
                    }
                }
            } catch (e: Exception) {
                // Maneja cualquier error aquí
                Log.e("TAGY", "Error al obtener detalles de ubicación: ${e.message}")
            }
        }
        return data
    }
}
