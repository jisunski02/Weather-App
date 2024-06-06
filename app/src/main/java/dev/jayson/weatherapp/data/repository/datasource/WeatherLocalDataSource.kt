package dev.jayson.weatherapp.data.repository.datasource

import dev.jayson.weatherapp.data.model.WeatherRoomData
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {

    suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData)

    suspend fun getWeatherRoomData(): Flow<WeatherRoomData>

    suspend fun deleteAllWeatherRoomData()
}