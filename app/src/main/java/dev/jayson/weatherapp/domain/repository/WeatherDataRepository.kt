package dev.jayson.weatherapp.domain.repository

import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherDataRepository {

    suspend fun getWeatherData(latitude: String, longitude: String, appId: String): Resource<WeatherData>

    suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData)

    suspend fun getWeatherRoomData(): Flow<List<WeatherRoomData>>

    suspend fun deleteAllWeatherRoomData()
}