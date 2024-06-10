package dev.jayson.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherRemoteViewModel(private val getWeatherRemoteDataUseCase: GetWeatherRemoteDataUseCase): ViewModel() {

      private val weatherMutableStateFlow: MutableStateFlow<Resource<WeatherData>> = MutableStateFlow(Resource.Loading())
      val weatherStateFlow: StateFlow<Resource<WeatherData>>
          get() = weatherMutableStateFlow



    fun getWeatherData(latitude: String, longitude: String, appId: String){
        if(weatherStateFlow.value.data == null){
            viewModelScope.launch {

                weatherMutableStateFlow.value = Resource.Loading()

                try{
                    val weatherData = getWeatherRemoteDataUseCase.invoke(latitude, longitude, appId)
                    weatherMutableStateFlow.value = weatherData
                }
                catch (e: Exception){
                    weatherMutableStateFlow.value = Resource.Error(e.toString())
                }

            }
        }

    }

}