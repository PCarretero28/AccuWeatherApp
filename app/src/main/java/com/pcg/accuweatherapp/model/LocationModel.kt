package com.pcg.accuweatherapp.model

data class LocationModel(
    val Key: String,
    val LocalizedName: String,
    val EnglishName: String,
    val Region: RegionModel,
    val Country: CountryModel
)
