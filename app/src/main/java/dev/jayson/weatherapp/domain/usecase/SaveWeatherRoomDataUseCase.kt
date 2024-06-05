package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository

class SaveWeatherRoomDataUseCase(private val weatherDataRepository: WeatherDataRepository) {

    suspend fun invoke(weatherRoomData: WeatherRoomData){
        return weatherDataRepository.saveWeatherRoomData(weatherRoomData)
    }
}