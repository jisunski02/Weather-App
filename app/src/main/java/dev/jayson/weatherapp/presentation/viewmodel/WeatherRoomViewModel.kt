package dev.jayson.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WeatherRoomViewModel(private val getWeatherRoomDataUseCase: GetWeatherRoomDataUseCase,
                           private val saveWeatherRoomDataUseCase: SaveWeatherRoomDataUseCase): ViewModel() {

    private val isDataSavedToRoom: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDataSaved: StateFlow<Boolean>
        get() = isDataSavedToRoom

    fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) = viewModelScope.launch {
        saveWeatherRoomDataUseCase.invoke(weatherRoomData)
        isDataSavedToRoom.value = true
    }

    fun getWeatherRoomData() = flow {
        getWeatherRoomDataUseCase.invoke().collect{
            emit(it)
        }
    }
}