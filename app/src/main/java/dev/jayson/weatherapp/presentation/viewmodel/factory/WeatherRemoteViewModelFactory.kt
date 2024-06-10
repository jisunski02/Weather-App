package dev.jayson.weatherapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRemoteViewModel

@Suppress("UNCHECKED_CAST")
class WeatherRemoteViewModelFactory(private val getWeatherRemoteDataUseCase: GetWeatherRemoteDataUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherRemoteViewModel(getWeatherRemoteDataUseCase) as T
    }
}