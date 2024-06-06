package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.domain.repository.WeatherDataRepository

class DeleteWeatherRoomDataUseCase(private val weatherDataRepository: WeatherDataRepository) {

    suspend fun invoke(){
        weatherDataRepository.deleteAllWeatherRoomData()
    }
}