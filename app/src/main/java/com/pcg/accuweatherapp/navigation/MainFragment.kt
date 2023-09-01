package com.pcg.accuweatherapp.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pcg.accuweatherapp.R
import com.pcg.accuweatherapp.databinding.ActivityMainBinding
import com.pcg.accuweatherapp.model.location.LocationModel
import com.pcg.accuweatherapp.service.LocationService
import com.pcg.accuweatherapp.viewmodel.LocationViewModel

class MainFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var location: LocationModel
    private var keyLocation: String = "a"
    private val locationService: LocationService = LocationService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}