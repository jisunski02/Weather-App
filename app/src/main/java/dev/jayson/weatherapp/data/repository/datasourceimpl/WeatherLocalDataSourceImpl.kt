package dev.jayson.weatherapp.data.repository.datasourceimpl

import dev.jayson.weatherapp.data.db.WeatherRoomDataDao
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.repository.datasource.WeatherLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherLocalDataSourceImpl(private val weatherRoomDataDao: WeatherRoomDataDao): WeatherLocalDataSource {

    override suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRoomDataDao.saveWeatherRoomData(weatherRoomData)
        }
    }

    override suspend fun getWeatherRoomData(): Flow<List<WeatherRoomData>> {
        return weatherRoomDataDao.getWeatherRoomData()
    }

    override suspend fun deleteAllWeatherRoomData() {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRoomDataDao.deleteAllWeatherRoomData()
        }
    }
}