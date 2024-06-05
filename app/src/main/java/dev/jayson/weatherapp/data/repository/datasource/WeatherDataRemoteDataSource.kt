package dev.jayson.weatherapp.data.repository.datasource

import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.model.WeatherRoomData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherDataRemoteDataSource {

    suspend fun getWeatherData(latitude: String, longitude: String, appId: String): Response<WeatherData>

}