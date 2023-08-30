package com.pcg.accuweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pcg.accuweatherapp.databinding.ActivityMainBinding
import com.pcg.accuweatherapp.model.LocationModel
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var location: LocationModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationViewModel = ViewModelProvider(this)
            .get(LocationViewModel::class.java)

        //Display the data
        GlobalScope.launch(Dispatchers.Main){
            locationViewModel.getLocationFromLiveData().observe(this@MainActivity, Observer {
                if(it != null){
                    location = it

                    binding.mainCity.text = location.LocalizedName
                    binding.mainCountryRegion.text = "${location.Country.LocalizedName}, ${location.Region.LocalizedName}"

                }else{
                    binding.mainCity.text = "No location found"
                }
            })
        }



    }


}