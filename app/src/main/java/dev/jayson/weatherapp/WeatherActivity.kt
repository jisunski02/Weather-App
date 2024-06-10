package dev.jayson.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.jayson.weatherapp.databinding.ActivityWeatherBinding
import dev.jayson.weatherapp.presentation.adapter.PastWeatherAdapter
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRemoteViewModel
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRoomViewModel
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherRemoteViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherRoomViewModelFactory
import javax.inject.Inject


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherRemoteViewModelFactory: WeatherRemoteViewModelFactory
    @Inject
    lateinit var weatherRoomViewModelFactory: WeatherRoomViewModelFactory
    @Inject
    lateinit var pastWeatherAdapter: PastWeatherAdapter

    private lateinit var binding: ActivityWeatherBinding
    lateinit var weatherRemoteViewModel: WeatherRemoteViewModel
    lateinit var weatherRoomViewModel: WeatherRoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvWeather.setupWithNavController(
            navController
        )

        weatherRemoteViewModel = ViewModelProvider(this, weatherRemoteViewModelFactory)[WeatherRemoteViewModel::class.java]
        weatherRoomViewModel = ViewModelProvider(this, weatherRoomViewModelFactory)[WeatherRoomViewModel::class.java]

    }
}