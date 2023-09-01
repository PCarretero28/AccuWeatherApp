package com.pcg.accuweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pcg.accuweatherapp.databinding.ActivityMainBinding
import com.pcg.accuweatherapp.model.LocationModel
import com.pcg.accuweatherapp.service.LocationService
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var location: LocationModel
    private var keyLocation: String = "a"
    private val locationService: LocationService = LocationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationViewModel = ViewModelProvider(this)
            .get(LocationViewModel::class.java)

        reloadLocation()

        binding.btnReload.setOnClickListener {
            reloadLocation()
        }
    }


    private fun reloadLocation(){
        Log.i("getLocation","Enter reloadLocation")
        lifecycleScope.launch{
            val result = locationService.getUserLocation(this@MainActivity)

            if(result != null){
                val geoPosition = "${result.latitude}, ${result.longitude}"
                binding.geoPosition.text = geoPosition
                Log.i("getLocation",geoPosition)

                locationViewModel.getLocationFromLiveData(geoPosition).observe(this@MainActivity, Observer {
                    Log.i("getLocation","Enter getLocationFromLiveData")

                    if(it != null){
                        Log.i("getLocation","it is not null")
                        location = it

                        if(location.LocalizedName.isNotEmpty()){
                            binding.mainCity.text = location.LocalizedName
                        }
                        else{
                            binding.mainCity.text = location.EnglishName
                        }
                        binding.mainCountryRegion.text = "${location.Country.LocalizedName}, ${location.Region.LocalizedName}"

                        //Show the current weather

                        keyLocation = location.Key

                    }else{
                        binding.mainCity.text = getString(R.string.error_finding_location)
                    }
                })
            }
            else{
                binding.geoPosition.text = getString(R.string.error_finding_location)
            }
        }
    }


}