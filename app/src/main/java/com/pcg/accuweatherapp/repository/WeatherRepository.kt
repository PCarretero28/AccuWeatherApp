package com.pcg.accuweatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pcg.accuweatherapp.model.currentweather.CurrentWeatherModel
import com.pcg.accuweatherapp.model.forecastweather.ForecastWeatherModel
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

                if (response.isSuccessful) {
                    // Guarda los datos en la lista
                    val location = response.body()

                    if (location != null) {
                        data.postValue(location!!)
                    }
                }
            } catch (e: Exception) {
                Log.e("TAGY", "Error al obtener detalles de ubicación: ${e.message}")
            }
        }
        return data
    }

    fun getCurrentWeatherFromAPI(id: String): LiveData<List<CurrentWeatherModel>> {
        val data = MutableLiveData<List<CurrentWeatherModel>>()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Devuelve Response<List<CurrentWeatherModel>>
                val response = accuWeatherAPI.getCurrentWeather(id)
                Log.i("WeatherRepository", response.toString())

                if (response.isSuccessful) {
                    val weather = response.body()

                    if (weather != null) {
                        data.postValue(weather!!)
                    }
                }
            } catch (e: Exception) {
                Log.e("TAGY", "Error al obtener detalles de tiempo actual: ${e.message}")
            }
        }
        return data
    }

    fun getForecastWeatherFromAPI(id: String): LiveData<ForecastWeatherModel> {
        val data = MutableLiveData<ForecastWeatherModel>()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Devuelve Response<ForecastWeatherModel>
                val response = accuWeatherAPI.getForecastWeather(id)
                Log.i("WeatherRepository", response.toString())

                if (response.isSuccessful) {
                    val weather = response.body()

                    if (weather != null) {
                        data.postValue(weather!!)
                    }
                }
            } catch (e: Exception) {
                Log.e("TAGY", "Error al obtener detalles de tiempo actual: ${e.message}")
            }
        }
        return data
    }


}
