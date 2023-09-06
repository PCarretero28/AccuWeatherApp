package com.pcg.accuweatherapp.model.forecastweather

import com.pcg.accuweatherapp.model.currentweather.MetricModel

data class TemperatureMinMaxModel(
    val Minimum: MetricModel,
    val Maximum: MetricModel
)
