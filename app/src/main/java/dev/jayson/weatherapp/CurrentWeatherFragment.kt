package dev.jayson.weatherapp

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.databinding.FragmentCurrentWeatherBinding
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRemoteViewModel
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRoomViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CurrentWeatherFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding: FragmentCurrentWeatherBinding
    private lateinit var weatherRemoteViewModel: WeatherRemoteViewModel
    private lateinit var weatherRoomViewModel: WeatherRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentWeatherBinding.bind(view)

        weatherRemoteViewModel = (activity as WeatherActivity).weatherRemoteViewModel
        weatherRoomViewModel = (activity as WeatherActivity).weatherRoomViewModel

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        checkLocationPermission()
        getWeatherData()
    }

    private fun getWeatherData(){

        viewLifecycleOwner.lifecycleScope.launch {
            // Repeat this coroutine as long as the lifecycle is in the CREATED state
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {

                //move this to checkLocation
                weatherRemoteViewModel.weatherStateFlow.collect{ resource->

                    when(resource){
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.linearLayoutCompat.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.linearLayoutCompat.visibility = View.VISIBLE

                            resource.data.let {weatherData->

                                if(weatherData!!.weather[0].main.contains("Clouds")){
                                    binding.ivWeather.setImageResource(R.drawable.ic_cloudy)
                                }

                                if(weatherData.weather[0].main.contains("Rain")){
                                    binding.ivWeather.setImageResource(R.drawable.ic_rainy)
                                }

                                if(weatherData.weather[0].main == "Sun"){
                                    binding.ivWeather.setImageResource(R.drawable.ic_sunny)
                                }

                                binding.tvTemperature.text = "${kelvinToCelsius(weatherData.main.temp)}\u00B0C"
                                binding.tvLocation.text = "${weatherData.name}, ${weatherData.sys.country}"
                                binding.tvWeather.text = weatherData.weather[0].main
                                binding.tvSunrise.text = unixTimestampTo12HrTimeHourMinute(weatherData.sys.sunrise)
                                binding.tvSunset.text = unixTimestampTo12HrTimeHourMinute(weatherData.sys.sunset)

                                //Save weather data in room DB everytime app opens
                                var count = 0
                                count++

                                weatherRoomViewModel.isDataSaved.collect{ isStateSaved->


                                    Log.e("Count", "$count")

                                    if(isStateSaved){
                                        Log.e("Saved", "No")
                                    }

                                    else{
                                        val weatherRoomData = WeatherRoomData(city = weatherData.name, country = weatherData.sys.country,
                                            weatherType = weatherData.weather[0].main, temperature = "${kelvinToCelsius(weatherData.main.temp)}\u00B0C",
                                            timeSunrise = unixTimestampTo12HrTimeHourMinute(weatherData.sys.sunrise), timeSunset = unixTimestampTo12HrTimeHourMinute(weatherData.sys.sunset))
                                        weatherRoomViewModel.saveWeatherRoomData(weatherRoomData)
                                        Log.e("Saved", "Yes")
                                    }

                                }

                            }
                        }

                        is Resource.Error -> {
                            Toast.makeText(activity, "Failed to load data", Toast.LENGTH_LONG).show()
                        }

                    }

                }

            }

            }
    }

    fun kelvinToCelsius(kelvin: Double): String {
        val celsius = kelvin - 273.15
        return String.format("%.2f", celsius)
    }

    fun unixTimestampTo12HrTimeHourMinute(timestamp: Int): String {
        // Convert Unix timestamp to milliseconds
        val date = Date(timestamp.toLong() * 1000L)

        // Format the date in 12-hour time format with only hour and minutes
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // Permission is already granted
            getLastKnownLocation()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            getLastKnownLocation()
        } else {
            Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Use the latitude and longitude as needed
                    //Toast.makeText(requireContext(), "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_LONG).show()
                    //Log.e("latitude", "$latitude $longitude")
                    weatherRemoteViewModel.getWeatherData(latitude.toString(), longitude.toString(), BuildConfig.API_KEY)
                    Log.e("ApiCall", "Success")

                } else {
                    Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
                }
            }
    }

}