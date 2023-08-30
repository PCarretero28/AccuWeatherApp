package com.pcg.accuweatherapp.model

data class LocationModel(
    val Key: String,
    val LocalizedName: String,
    val Region: RegionModel,
    val Country: CountryModel
)
