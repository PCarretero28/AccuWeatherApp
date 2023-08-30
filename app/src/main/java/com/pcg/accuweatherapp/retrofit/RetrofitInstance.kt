package com.pcg.accuweatherapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val base_Url = "http://dataservice.accuweather.com/"

    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}