package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.domain.repository.WeatherDataRepository

class DeleteWeatherRoomData(private val weatherDataRepository: WeatherDataRepository) {

    suspend fun invoke(){
        weatherDataRepository.deleteAllWeatherRoomData()
    }
}