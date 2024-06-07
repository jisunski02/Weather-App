package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherRoomDataUseCase(private val weatherDataRepository: WeatherDataRepository) {

    suspend fun invoke(): Flow<List<WeatherRoomData>>{
        return weatherDataRepository.getWeatherRoomData()
    }

}