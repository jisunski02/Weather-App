package dev.jayson.weatherapp.data.api

import dev.jayson.weatherapp.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String,
        @Query("appId")
        appId: String
    ): Response<WeatherData>

}