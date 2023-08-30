package com.pcg.accuweatherapp.retrofit

import com.pcg.accuweatherapp.model.LocationModel
import retrofit2.Response
import retrofit2.http.GET

interface AccuWeatherAPI {

    @GET("/locations/v1/cities/geoposition/search?apikey=m0CLj239gGRjerrGbedSe15OaTpq6OF3&q=40.959024%2C-5.662175&language=es-ES&details=false")
    suspend fun getLocationDetails(): Response<LocationModel>

}