package com.pcg.accuweatherapp.model.currentweather

data class CurrentWeatherModel(
    val LocalObservationDateTime: String,
    val WeatherText: String,
    val IsDayTime: Boolean,
    val HasPrecipitation: Boolean,
    val Temperature: TemperatureModel
)
