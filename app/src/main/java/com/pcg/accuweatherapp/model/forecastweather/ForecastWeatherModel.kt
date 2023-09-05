package com.pcg.accuweatherapp.model.forecastweather

data class ForecastWeatherModel(
    val Headline: HeadlineModel,
    val DailyForecasts: List<DailyForecastModel>
)
