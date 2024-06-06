package dev.jayson.weatherapp.data.repository.datasource

import dev.jayson.weatherapp.data.model.WeatherData
import retrofit2.Response

interface WeatherRemoteDataSource {

    suspend fun getWeatherData(latitude: String, longitude: String, appId: String): Response<WeatherData>

}