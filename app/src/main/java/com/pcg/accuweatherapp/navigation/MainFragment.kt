package com.pcg.accuweatherapp.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pcg.accuweatherapp.R
import com.pcg.accuweatherapp.databinding.FragmentMainBinding
import com.pcg.accuweatherapp.model.currentweather.CurrentWeatherModel
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.service.LocationCallback
import com.pcg.accuweatherapp.service.LocationService
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var location: LocationModel
    private lateinit var currentWeather: List<CurrentWeatherModel>

    private lateinit var locationCallback: LocationCallback

    private val locationService: LocationService = LocationService()

    private lateinit var activityContext: Context

    private val keyLocationLiveData = MutableLiveData<String>()

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

        locationCallback = object : LocationCallback {
            override fun onLocationKeyReceived(key: String) {
                keyLocationLiveData.postValue(key)
            }
        }

        //Show location and get key
        reloadLocation()

        //Show current weather
        showCurrentWeather()

        binding.btnReload.setOnClickListener {
            reloadLocation()
            showCurrentWeather()
        }

        binding.btnForecast.setOnClickListener {
            Log.i("keyLocation", keyLocationLiveData.value ?: "KeyLocation not available")

            keyLocationLiveData.value?.let { keyLocation ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToForecastFragment(
                        id = keyLocation
                    )
                )
            }
        }

        return binding.root
    }

    private fun showCurrentWeather() {
        keyLocationLiveData.observe(viewLifecycleOwner, Observer { keyLocation ->
            Log.i("Weather", keyLocation)

            lifecycleScope.launch {
                locationViewModel.getCurrentWeatherFromKey(keyLocation)
                    .observe(viewLifecycleOwner, Observer {
                        Log.i("Weather", it.toString())
                        if (it != null) {
                            currentWeather = it

                            binding.tvTemperature.text = currentWeather[0].WeatherText
                            binding.tvCelsius.text = "${currentWeather[0].Temperature.Metric.Value} °C"
                        }
                    })
            }

        })
    }

    private fun reloadLocation() {
        lifecycleScope.launch {
            val result = locationService.getUserLocation(activityContext)

            if (result != null) {
                val geoPosition = "${result.latitude}, ${result.longitude}"
                binding.geoPosition.text = geoPosition

                locationViewModel.getLocationFromLiveData(geoPosition)
                    .observe(viewLifecycleOwner, Observer {

                        if (it != null) {
                            location = it
                            if (location.LocalizedName.isNotEmpty()) {
                                binding.mainCity.text = location.LocalizedName
                            } else {
                                binding.mainCity.text = location.EnglishName
                            }

                            binding.mainCountryRegion.text =
                                "${location.Country.LocalizedName}, ${location.Region.LocalizedName}"

                            val keyLocationValue = location.Key
                            keyLocationLiveData.postValue(keyLocationValue)

                            locationCallback.onLocationKeyReceived(location.Key)

                        } else {
                            binding.mainCity.text = getString(R.string.error_finding_location)
                        }
                    })
            } else {
                binding.geoPosition.text = getString(R.string.error_finding_location)
            }
        }
    }


}
