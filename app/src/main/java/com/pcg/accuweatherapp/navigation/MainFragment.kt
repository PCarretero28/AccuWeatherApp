package com.pcg.accuweatherapp.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pcg.accuweatherapp.R
import com.pcg.accuweatherapp.databinding.FragmentMainBinding
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.service.LocationService
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var location: LocationModel
    private var keyLocation: String = "a"
    private val locationService: LocationService = LocationService()

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        locationViewModel = ViewModelProvider(this)
            .get(LocationViewModel::class.java)

        reloadLocation()

        binding.btnReload.setOnClickListener {
            reloadLocation()
        }

        binding.btnForecast.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_forecastFragment)
        }

        return binding.root
    }

    private fun reloadLocation(){
        Log.i("getLocation","Enter reloadLocation")
        lifecycleScope.launch{
            val result = locationService.getUserLocation(activityContext)

            if(result != null){
                val geoPosition = "${result.latitude}, ${result.longitude}"
                binding.geoPosition.text = geoPosition
                Log.i("getLocation",geoPosition)

                locationViewModel.getLocationFromLiveData(geoPosition).observe(viewLifecycleOwner , Observer {
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