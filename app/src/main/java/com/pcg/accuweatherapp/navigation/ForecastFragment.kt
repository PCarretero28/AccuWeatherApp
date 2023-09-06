package com.pcg.accuweatherapp.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pcg.accuweatherapp.R
import com.pcg.accuweatherapp.databinding.FragmentForecastBinding
import com.pcg.accuweatherapp.model.forecastweather.ForecastWeatherModel
import com.pcg.accuweatherapp.viewmodel.LocationViewModel
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat

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

        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val tvText = view.findViewById<TextView>(R.id.tvText)

        //Card1
        val date1 = view.findViewById<TextView>(R.id.tvDate1)
        val min1 = view.findViewById<TextView>(R.id.tvMinTemperature1)
        val max1 = view.findViewById<TextView>(R.id.tvMaxTemperature1)
        val img1 = view.findViewById<ImageView>(R.id.imgDay1)
        val imgNight1 = view.findViewById<ImageView>(R.id.imgNight1)

        //Card2
        val date2 = view.findViewById<TextView>(R.id.tvDate2)
        val min2 = view.findViewById<TextView>(R.id.tvMinTemperature2)
        val max2 = view.findViewById<TextView>(R.id.tvMaxTemperature2)
        val img2 = view.findViewById<ImageView>(R.id.imgDay2)
        val imgNight2 = view.findViewById<ImageView>(R.id.imgNight2)

        //Card3
        val date3 = view.findViewById<TextView>(R.id.tvDate3)
        val min3 = view.findViewById<TextView>(R.id.tvMinTemperature3)
        val max3 = view.findViewById<TextView>(R.id.tvMaxTemperature3)
        val img3 = view.findViewById<ImageView>(R.id.imgDay3)
        val imgNight3 = view.findViewById<ImageView>(R.id.imgNight3)

        //Card4
        val date4 = view.findViewById<TextView>(R.id.tvDate4)
        val min4 = view.findViewById<TextView>(R.id.tvMinTemperature4)
        val max4 = view.findViewById<TextView>(R.id.tvMaxTemperature4)
        val img4 = view.findViewById<ImageView>(R.id.imgDay4)
        val imgNight4 = view.findViewById<ImageView>(R.id.imgNight4)

        //Card5
        val date5 = view.findViewById<TextView>(R.id.tvDate5)
        val min5 = view.findViewById<TextView>(R.id.tvMinTemperature5)
        val max5 = view.findViewById<TextView>(R.id.tvMaxTemperature5)
        val img5 = view.findViewById<ImageView>(R.id.imgDay5)
        val imgNight5 = view.findViewById<ImageView>(R.id.imgNight5)


        lifecycleScope.launch {
            locationViewModel.getForecastWeatherFromKey(id)
                .observe(viewLifecycleOwner, Observer {
                    Log.i("ForecastWeather", it.toString())
                    if(it!=null) {
                        forecastWeather = it

                        //Description
                        tvText.text = forecastWeather.Headline.Text

                        //Card1
                        var rawDate = forecastWeather.DailyForecasts[0].Date
                        var formattedDate = formatDateString(rawDate)
                        date1.text = formattedDate
                        min1.text = forecastWeather.DailyForecasts[0].Temperature.Minimum.Value.toString()
                        max1.text = forecastWeather.DailyForecasts[0].Temperature.Maximum.Value.toString()
                        when(forecastWeather.DailyForecasts[0].Day.Icon){
                            1,33 -> img1.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> img1.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> img1.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> img1.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> img1.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> img1.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> img1.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> img1.setImageResource(R.drawable.ic8_viento)
                            else -> img1.setImageResource(R.drawable.ic8_aviso)
                        }
                        when(forecastWeather.DailyForecasts[0].Night.Icon){
                            1,33 -> imgNight1.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> imgNight1.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> imgNight1.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> imgNight1.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> imgNight1.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> imgNight1.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> imgNight1.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> imgNight1.setImageResource(R.drawable.ic8_viento)
                            else -> imgNight1.setImageResource(R.drawable.ic8_aviso)
                        }

                        //Card2
                        rawDate = forecastWeather.DailyForecasts[1].Date
                        formattedDate = formatDateString(rawDate)
                        date2.text = formattedDate
                        min2.text = forecastWeather.DailyForecasts[1].Temperature.Minimum.Value.toString()
                        max2.text = forecastWeather.DailyForecasts[1].Temperature.Maximum.Value.toString()
                        when(forecastWeather.DailyForecasts[1].Day.Icon){
                            1,33 -> img2.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> img2.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> img2.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> img2.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> img2.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> img2.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> img2.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> img2.setImageResource(R.drawable.ic8_viento)
                            else -> img2.setImageResource(R.drawable.ic8_aviso)
                        }
                        when(forecastWeather.DailyForecasts[1].Night.Icon){
                            1,33 -> imgNight2.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> imgNight2.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> imgNight2.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> imgNight2.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> imgNight2.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> imgNight2.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> imgNight2.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> imgNight2.setImageResource(R.drawable.ic8_viento)
                            else -> imgNight2.setImageResource(R.drawable.ic8_aviso)
                        }

                        //Card3
                        rawDate = forecastWeather.DailyForecasts[2].Date
                        formattedDate = formatDateString(rawDate)
                        date3.text = formattedDate
                        min3.text = forecastWeather.DailyForecasts[2].Temperature.Minimum.Value.toString()
                        max3.text = forecastWeather.DailyForecasts[2].Temperature.Maximum.Value.toString()
                        when(forecastWeather.DailyForecasts[2].Day.Icon){
                            1,33 -> img3.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> img3.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> img3.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> img3.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> img3.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> img3.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> img3.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> img3.setImageResource(R.drawable.ic8_viento)
                            else -> img3.setImageResource(R.drawable.ic8_aviso)
                        }
                        when(forecastWeather.DailyForecasts[2].Night.Icon){
                            1,33 -> imgNight3.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> imgNight3.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> imgNight3.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> imgNight3.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> imgNight3.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> imgNight3.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> imgNight3.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> imgNight3.setImageResource(R.drawable.ic8_viento)
                            else -> imgNight3.setImageResource(R.drawable.ic8_aviso)
                        }

                        //Card4
                        rawDate = forecastWeather.DailyForecasts[3].Date
                        formattedDate = formatDateString(rawDate)
                        date4.text = formattedDate
                        min4.text = forecastWeather.DailyForecasts[3].Temperature.Minimum.Value.toString()
                        max4.text = forecastWeather.DailyForecasts[3].Temperature.Maximum.Value.toString()
                        when(forecastWeather.DailyForecasts[3].Day.Icon){
                            1,33 -> img4.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> img4.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> img4.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> img4.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> img4.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> img4.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> img4.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> img4.setImageResource(R.drawable.ic8_viento)
                            else -> img4.setImageResource(R.drawable.ic8_aviso)
                        }
                        when(forecastWeather.DailyForecasts[3].Night.Icon){
                            1,33 -> imgNight4.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> imgNight4.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> imgNight4.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> imgNight4.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> imgNight4.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> imgNight4.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> imgNight4.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> imgNight4.setImageResource(R.drawable.ic8_viento)
                            else -> imgNight4.setImageResource(R.drawable.ic8_aviso)
                        }

                        //Card5
                        rawDate = forecastWeather.DailyForecasts[4].Date
                        formattedDate = formatDateString(rawDate)
                        date5.text = formattedDate
                        min5.text = forecastWeather.DailyForecasts[4].Temperature.Minimum.Value.toString()
                        max5.text = forecastWeather.DailyForecasts[4].Temperature.Maximum.Value.toString()
                        when(forecastWeather.DailyForecasts[4].Day.Icon){
                            1,33 -> img5.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> img5.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> img5.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> img5.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> img5.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> img5.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> img5.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> img5.setImageResource(R.drawable.ic8_viento)
                            else -> img5.setImageResource(R.drawable.ic8_aviso)
                        }
                        when(forecastWeather.DailyForecasts[4].Night.Icon){
                            1,33 -> imgNight5.setImageResource(R.drawable.ic8_sol)
                            2,3,34,35 -> imgNight5.setImageResource(R.drawable.ic8_parcialmente_soleado)
                            4,5,6,36 -> imgNight5.setImageResource(R.drawable.ic8_parcialmente_nublado)
                            7,8,37,38 -> imgNight5.setImageResource(R.drawable.ic8_nube)
                            12,13,14,39,40 -> imgNight5.setImageResource(R.drawable.ic8_lluvia)
                            15,41,42 -> imgNight5.setImageResource(R.drawable.ic8_tormenta)
                            22,23,24,44 -> imgNight5.setImageResource(R.drawable.ic8_hielo)
                            32,43 -> imgNight5.setImageResource(R.drawable.ic8_viento)
                            else -> imgNight5.setImageResource(R.drawable.ic8_aviso)
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

    @SuppressLint("SimpleDateFormat")
    private fun formatDateString(rawDate: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = inputFormat.parse(rawDate)
            return outputFormat.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return rawDate // Devuelve la cadena original si hay un error
    }


}