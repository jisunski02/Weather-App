package dev.jayson.weatherapp.presentation.di.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.data.db.UserDataDao
import dev.jayson.weatherapp.data.db.WeatherRoomDataDao
import dev.jayson.weatherapp.data.db.WeatherRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun providesWeatherRoomDatabase(app: Application): WeatherRoomDatabase{
        return Room.databaseBuilder(app, WeatherRoomDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesUserDataDao(weatherRoomDatabase: WeatherRoomDatabase): UserDataDao{
        return weatherRoomDatabase.getUserDataDao()
    }

    @Singleton
    @Provides
    fun providesWeatherRoomDataDao(weatherRoomDatabase: WeatherRoomDatabase): WeatherRoomDataDao{
        return  weatherRoomDatabase.getWeatherDataDao()
    }

}