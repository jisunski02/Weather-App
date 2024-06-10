package dev.jayson.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.databinding.ActivityLoginBinding
import dev.jayson.weatherapp.presentation.viewmodel.LoginUserViewModel
import dev.jayson.weatherapp.presentation.viewmodel.RegisterUserViewModel
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRemoteViewModel
import dev.jayson.weatherapp.presentation.viewmodel.factory.LoginUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.RegisterUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherRemoteViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var weatherRemoteViewModelFactory: WeatherRemoteViewModelFactory
    @Inject
    lateinit var registerUserViewModelFactory: RegisterUserViewModelFactory
    @Inject
    lateinit var loginUserViewModelFactory: LoginUserViewModelFactory

    lateinit var binding: ActivityLoginBinding
    lateinit var weatherRemoteViewModel: WeatherRemoteViewModel
    lateinit var registerUserViewModel: RegisterUserViewModel
    lateinit var loginUserViewModel: LoginUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]
          registerUserViewModel = ViewModelProvider(this, registerUserViewModelFactory)[RegisterUserViewModel::class.java]
          loginUserViewModel = ViewModelProvider(this, loginUserViewModelFactory)[LoginUserViewModel::class.java]
//
//        //getWeatherData("14.5745631", "121.047822", "d7b062ae5d0b865a8ab3a2bf9d8ba9df")
//
//
//        //registerUser(userData)
//
//        loginUser("jayson3", "pogipogi3")

    }



    private fun loginUser(username: String, password: String){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                loginUserViewModel.loginUser(username, password).collect{userData->

                    try{
                        Log.e("Hello", "${userData.username} ${userData.password}")
                    }

                    catch (e: Exception){
                        Log.e("Hello", "Not Existing")
                    }
                }

            }

        }
    }

    private fun getWeatherData(latitude: String, longitude: String, appId: String){

        weatherRemoteViewModel.getWeatherData(latitude, longitude, appId)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                weatherRemoteViewModel.weatherStateFlow.collect{ resource ->

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