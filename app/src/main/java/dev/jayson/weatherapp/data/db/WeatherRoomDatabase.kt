package dev.jayson.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.data.model.WeatherRoomData

@Database(
    entities = [WeatherRoomData::class, UserData::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherRoomDatabase: RoomDatabase() {

    abstract fun getWeatherDataDao(): WeatherRoomDataDao
    abstract fun getUserDataDao(): UserDataDao

}