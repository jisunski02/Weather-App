package dev.jayson.weatherapp.data.repository

import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.repository.datasource.WeatherDataLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherDataRemoteDataSource
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherDataRepositoryImpl(private val weatherDataRemoteDataSource: WeatherDataRemoteDataSource,
                                private val weatherDataLocalDataSource: WeatherDataLocalDataSource): WeatherDataRepository {

    private fun responseToResource(response: Response<WeatherData>): Resource<WeatherData> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override suspend fun getWeatherData(
        latitude: String,
        longitude: String,
        appId: String
    ): Resource<WeatherData> {
        return responseToResource(weatherDataRemoteDataSource.getWeatherData(latitude, longitude, appId))
    }

    override suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) {
       return weatherDataLocalDataSource.saveWeatherRoomData(weatherRoomData)
    }

    override suspend fun getWeatherRoomData(): Flow<WeatherRoomData> {
        return weatherDataLocalDataSource.getWeatherRoomData()
    }

    override suspend fun deleteAllWeatherRoomData() {
        return weatherDataLocalDataSource.deleteAllWeatherRoomData()
    }
}