package dev.jayson.weatherapp.data.repository.datasourceimpl

import dev.jayson.weatherapp.data.api.ApiService
import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.repository.datasource.WeatherDataRemoteDataSource
import retrofit2.Response

class WeatherDataRemoteDataSourceImpl(private val apiService: ApiService): WeatherDataRemoteDataSource {

    override suspend fun getWeatherData(
        latitude: String,
        longitude: String,
        appId: String
    ): Response<WeatherData> {
        return apiService.getWeatherData(latitude, longitude, appId)
    }
}