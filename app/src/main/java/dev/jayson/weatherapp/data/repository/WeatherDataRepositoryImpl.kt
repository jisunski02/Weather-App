package dev.jayson.weatherapp.data.repository

import dev.jayson.weatherapp.data.model.WeatherData
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.repository.datasource.WeatherLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherRemoteDataSource
import dev.jayson.weatherapp.data.util.Resource
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherDataRepositoryImpl(private val weatherRemoteDataSource: WeatherRemoteDataSource,
                                private val weatherLocalDataSource: WeatherLocalDataSource): WeatherDataRepository {

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
        return responseToResource(weatherRemoteDataSource.getWeatherData(latitude, longitude, appId))
    }

    override suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) {
       return weatherLocalDataSource.saveWeatherRoomData(weatherRoomData)
    }

    override suspend fun getWeatherRoomData(): Flow<WeatherRoomData> {
        return weatherLocalDataSource.getWeatherRoomData()
    }

    override suspend fun deleteAllWeatherRoomData() {
        return weatherLocalDataSource.deleteAllWeatherRoomData()
    }
}