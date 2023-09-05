package com.pcg.accuweatherapp.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.pcg.accuweatherapp.R
import com.pcg.accuweatherapp.databinding.FragmentForecastBinding
import com.pcg.accuweatherapp.model.forecastweather.ForecastWeatherModel
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var forecastWeather: ForecastWeatherModel


    val args: ForecastFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel = ViewModelProvider(this)
            .get(LocationViewModel::class.java)

        val id = args.id

        val tvText = view.findViewById<TextView>(R.id.tvText)

        //Card1
        val date1 = view.findViewById<TextView>(R.id.tvDate1)
        val img1 = view.findViewById<ImageView>(R.id.img1)

        //Card2
        val date2 = view.findViewById<TextView>(R.id.tvDate2)
        val img2 = view.findViewById<ImageView>(R.id.img2)

        //Card3
        val date3 = view.findViewById<TextView>(R.id.tvDate3)
        val img3 = view.findViewById<ImageView>(R.id.img3)

        //Card3
        val date4 = view.findViewById<TextView>(R.id.tvDate4)
        val img4 = view.findViewById<ImageView>(R.id.img4)

        //Card5
        val date5 = view.findViewById<TextView>(R.id.tvDate5)
        val img5 = view.findViewById<ImageView>(R.id.img5)


        lifecycleScope.launch {
            locationViewModel.getForecastWeatherFromKey(id)
                .observe(viewLifecycleOwner, Observer {
                    Log.i("ForecastWeather", it.toString())
                    if(it!=null) {
                        forecastWeather = it

                        //Description
                        tvText.text = forecastWeather.Headline.Text

                        //Card1
                        date1.text = forecastWeather.DailyForecasts[0].Date
                        when(forecastWeather.DailyForecasts[0].Day.Icon){
                            1 -> img1.setImageResource(R.drawable.ic8_sol)
                            2,3 -> img1.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6 -> img1.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8 -> img1.setImageResource(R.drawable.ic8_nube)
                            12,13,14 -> img1.setImageResource(R.drawable.ic8_lluvia)
                            15 -> img1.setImageResource(R.drawable.ic8_tormenta)
                            24 -> img1.setImageResource(R.drawable.ic8_hielo)
                            32 -> img1.setImageResource(R.drawable.ic8_viento)
                            else -> img1.setImageResource(R.drawable.ic8_aviso)
                        }
                        //Card2
                        date2.text = forecastWeather.DailyForecasts[1].Date
                        when(forecastWeather.DailyForecasts[1].Day.Icon){
                            1 -> img2.setImageResource(R.drawable.ic8_sol)
                            2,3 -> img2.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6 -> img2.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8 -> img2.setImageResource(R.drawable.ic8_nube)
                            12,13,14 -> img2.setImageResource(R.drawable.ic8_lluvia)
                            15 -> img2.setImageResource(R.drawable.ic8_tormenta)
                            24 -> img2.setImageResource(R.drawable.ic8_hielo)
                            32 -> img2.setImageResource(R.drawable.ic8_viento)
                            else -> img2.setImageResource(R.drawable.ic8_aviso)
                        }
                        //Card3
                        date3.text = forecastWeather.DailyForecasts[2].Date
                        when(forecastWeather.DailyForecasts[2].Day.Icon){
                            1 -> img3.setImageResource(R.drawable.ic8_sol)
                            2,3 -> img3.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6 -> img3.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8 -> img3.setImageResource(R.drawable.ic8_nube)
                            12,13,14 -> img3.setImageResource(R.drawable.ic8_lluvia)
                            15 -> img3.setImageResource(R.drawable.ic8_tormenta)
                            24 -> img3.setImageResource(R.drawable.ic8_hielo)
                            32 -> img3.setImageResource(R.drawable.ic8_viento)
                            else -> img3.setImageResource(R.drawable.ic8_aviso)
                        }
                        //Card4
                        date4.text = forecastWeather.DailyForecasts[3].Date
                        when(forecastWeather.DailyForecasts[3].Day.Icon){
                            1 -> img4.setImageResource(R.drawable.ic8_sol)
                            2,3 -> img4.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6 -> img4.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8 -> img4.setImageResource(R.drawable.ic8_nube)
                            12,13,14 -> img4.setImageResource(R.drawable.ic8_lluvia)
                            15 -> img4.setImageResource(R.drawable.ic8_tormenta)
                            24 -> img4.setImageResource(R.drawable.ic8_hielo)
                            32 -> img4.setImageResource(R.drawable.ic8_viento)
                            else -> img4.setImageResource(R.drawable.ic8_aviso)
                        }

                        //Card5
                        date5.text = forecastWeather.DailyForecasts[4].Date
                        when(forecastWeather.DailyForecasts[4].Day.Icon){
                            1 -> img5.setImageResource(R.drawable.ic8_sol)
                            2,3 -> img5.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6 -> img5.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8 -> img5.setImageResource(R.drawable.ic8_nube)
                            12,13,14 -> img5.setImageResource(R.drawable.ic8_lluvia)
                            15 -> img5.setImageResource(R.drawable.ic8_tormenta)
                            24 -> img5.setImageResource(R.drawable.ic8_hielo)
                            32 -> img5.setImageResource(R.drawable.ic8_viento)
                            else -> img5.setImageResource(R.drawable.ic8_aviso)
                        }

                    }
                })
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(layoutInflater)

        return binding.root
    }


}