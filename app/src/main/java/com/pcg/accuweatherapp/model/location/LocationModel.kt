package com.pcg.accuweatherapp.model.location

data class LocationModel(
    val Key: String,
    val LocalizedName: String,
    val EnglishName: String,
    val Region: RegionModel,
    val Country: CountryModel
)
