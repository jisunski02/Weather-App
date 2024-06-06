package dev.jayson.weatherapp.presentation.di.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.data.api.ApiService
import dev.jayson.weatherapp.data.db.UserDataDao
import dev.jayson.weatherapp.data.db.WeatherRoomDataDao
import dev.jayson.weatherapp.data.repository.datasource.UserLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherRemoteDataSource
import dev.jayson.weatherapp.data.repository.datasourceimpl.UserLocalDataSourceImpl
import dev.jayson.weatherapp.data.repository.datasourceimpl.WeatherLocalDataSourceImpl
import dev.jayson.weatherapp.data.repository.datasourceimpl.WeatherRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesUserLocalDataSource(userDataDao: UserDataDao): UserLocalDataSource{
        return UserLocalDataSourceImpl(userDataDao)
    }

    @Singleton
    @Provides
    fun providesWeatherRemoteDataSource(apiService: ApiService): WeatherRemoteDataSource{
        return WeatherRemoteDataSourceImpl(apiService)
    }


    @Singleton
    @Provides
    fun providesWeatherLocalDataSource(weatherRoomDataDao: WeatherRoomDataDao): WeatherLocalDataSource{
        return WeatherLocalDataSourceImpl(weatherRoomDataDao)
    }


}