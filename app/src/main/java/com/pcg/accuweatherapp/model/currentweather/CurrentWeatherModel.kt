package com.pcg.accuweatherapp.model.currentweather

data class CurrentWeatherModel(
    val LocalObservationDateTime: String,
    val WeatherText: String,
    val WeatherIcon: Int,
    val HasPrecipitation: Boolean,
    val IsDayTime: Boolean,
    val Temperature: TemperatureModel
)

