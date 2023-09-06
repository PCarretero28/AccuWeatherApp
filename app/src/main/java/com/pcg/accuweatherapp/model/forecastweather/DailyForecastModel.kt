package com.pcg.accuweatherapp.model.forecastweather

data class DailyForecastModel(
    val Date: String,
    val Day: PhaseModel,
    val Night: PhaseModel,
    val Temperature: TemperatureMinMaxModel
)
