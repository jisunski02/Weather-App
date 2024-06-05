package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository

class GetWeatherRemoteDataUseCase(private val weatherDataRepository: WeatherDataRepository) {

    suspend fun invoke(latitude: String, longitude: String, appId: String): Resource<WeatherData>{
        return weatherDataRepository.getWeatherData(latitude, longitude, appId)
    }
}