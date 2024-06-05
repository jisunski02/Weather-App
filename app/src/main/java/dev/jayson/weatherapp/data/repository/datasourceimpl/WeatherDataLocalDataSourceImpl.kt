package dev.jayson.weatherapp.data.repository.datasourceimpl

import dev.jayson.weatherapp.data.db.WeatherRoomDataDao
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.data.repository.datasource.WeatherDataLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherDataLocalDataSourceImpl(private val weatherRoomDataDao: WeatherRoomDataDao): WeatherDataLocalDataSource {

    override suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRoomDataDao.saveWeatherRoomData(weatherRoomData)
        }
    }

    override suspend fun getWeatherRoomData(): Flow<WeatherRoomData> {
        return weatherRoomDataDao.getWeatherRoomData()
    }

    override suspend fun deleteAllWeatherRoomData() {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRoomDataDao.deleteAllWeatherRoomData()
        }
    }
}