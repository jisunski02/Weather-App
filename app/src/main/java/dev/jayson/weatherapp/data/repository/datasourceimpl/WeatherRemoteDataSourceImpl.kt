package dev.jayson.weatherapp.data.repository.datasourceimpl

import dev.jayson.weatherapp.data.api.ApiService
import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.repository.datasource.WeatherRemoteDataSource
import retrofit2.Response

class WeatherRemoteDataSourceImpl(private val apiService: ApiService): WeatherRemoteDataSource {

    override suspend fun getWeatherData(
        latitude: String,
        longitude: String,
        appId: String
    ): Response<WeatherData> {
        return apiService.getWeatherData(latitude, longitude, appId)
    }
}