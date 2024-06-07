package dev.jayson.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.jayson.weatherapp.R
import dev.jayson.weatherapp.databinding.ActivityWeatherBinding
import dev.jayson.weatherapp.presentation.adapter.PastWeatherAdapter
import dev.jayson.weatherapp.presentation.viewmodel.WeatherViewModel
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherViewModelFactory
import javax.inject.Inject


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory
    @Inject
    lateinit var pastWeatherAdapter: PastWeatherAdapter

    private lateinit var binding: ActivityWeatherBinding
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvWeather.setupWithNavController(
            navController
        )

        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]

    }
}