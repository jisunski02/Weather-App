package dev.jayson.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.databinding.ActivityMainBinding
import dev.jayson.weatherapp.presentation.viewmodel.WeatherViewModel
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]

        getWeatherData("14.5745631", "121.047822", "d7b062ae5d0b865a8ab3a2bf9d8ba9df")
    }

    private fun getWeatherData(latitude: String, longitude: String, appId: String){

        weatherViewModel.getWeatherData(latitude, longitude, appId)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                weatherViewModel.weatherStateFlow.collect{ resource ->

                    when(resource){
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            resource.data?.let {
                                it.weather.forEach{ weather->

                                    Log.e("weatherMain", weather.main)
                                }
                                Log.e("cityName", it.name)
                            }
                        }

                        is Resource.Error -> {
                            // Handle error state
                            resource.message?.let {
                                Log.e("weatherData", "An error occured : $it")
                            }
                        }
                    }

                }
            }
        }


    }
}