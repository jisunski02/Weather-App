package dev.jayson.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WeatherViewModel(private val getWeatherRemoteDataUseCase: GetWeatherRemoteDataUseCase,
                        private val getWeatherRoomDataUseCase: GetWeatherRoomDataUseCase,
                        private val saveWeatherRoomDataUseCase: SaveWeatherRoomDataUseCase): ViewModel() {

      private val weatherMutableStateFlow: MutableStateFlow<Resource<WeatherData>> = MutableStateFlow(Resource.Loading())
      val weatherStateFlow: StateFlow<Resource<WeatherData>>
          get() = weatherMutableStateFlow

    fun getWeatherData(latitude: String, longitude: String, appId: String){
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

    fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) = viewModelScope.launch {
        saveWeatherRoomDataUseCase.invoke(weatherRoomData)
    }

    fun getWeatherRoomData() = flow {
        getWeatherRoomDataUseCase.invoke().collect{
            emit(it)
        }
    }
}