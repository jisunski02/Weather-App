package dev.jayson.weatherapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRoomViewModel

@Suppress("UNCHECKED_CAST")
class WeatherRoomViewModelFactory(private val getWeatherRoomDataUseCase: GetWeatherRoomDataUseCase,
                                  private val saveWeatherRoomDataUseCase: SaveWeatherRoomDataUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherRoomViewModel(getWeatherRoomDataUseCase,
                                  saveWeatherRoomDataUseCase) as T
    }
}