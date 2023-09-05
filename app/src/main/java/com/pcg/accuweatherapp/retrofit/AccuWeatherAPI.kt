package com.pcg.accuweatherapp.retrofit

import com.pcg.accuweatherapp.model.currentweather.CurrentWeatherModel
import com.pcg.accuweatherapp.model.forecastweather.ForecastWeatherModel
import com.pcg.accuweatherapp.model.location.LocationModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuWeatherAPI {

    //Key Minsait = m0CLj239gGRjerrGbedSe15OaTpq6OF3
    //My Key = 	y4sPtDdk8X7haXdEkOpZ1aCUYXn8zsja

    //Get location
    @GET("/locations/v1/cities/geoposition/search?apikey=d7i25HptyA8y1l9DifwVJ6LETx7sGmUU&language=es-ES&details=false")
    suspend fun getLocationDetails(@Query("q") geoPosition: String): Response<LocationModel>

    //Current weather
    @GET("/currentconditions/v1/{id}?apikey=d7i25HptyA8y1l9DifwVJ6LETx7sGmUU")
    suspend fun getCurrentWeather(@Path("id") id: String): Response<List<CurrentWeatherModel>>

    //Forecast weather
    @GET("/forecasts/v1/daily/5day/{id}?apikey=d7i25HptyA8y1l9DifwVJ6LETx7sGmUU&language=es-ES&metric=true")
    suspend fun getForecastWeather(@Path("id") id: String): Response<ForecastWeatherModel>

}