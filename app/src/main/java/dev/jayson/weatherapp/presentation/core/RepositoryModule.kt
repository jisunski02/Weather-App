package dev.jayson.weatherapp.presentation.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.data.repository.UserDataRepositoryImpl
import dev.jayson.weatherapp.data.repository.WeatherDataRepositoryImpl
import dev.jayson.weatherapp.data.repository.datasource.UserLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherLocalDataSource
import dev.jayson.weatherapp.data.repository.datasource.WeatherRemoteDataSource
import dev.jayson.weatherapp.domain.repository.UserDataRepository
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesUserDataRepository(userLocalDataSource: UserLocalDataSource): UserDataRepository{
        return UserDataRepositoryImpl(userLocalDataSource)
    }

    @Singleton
    @Provides
    fun providesWeatherDataRepository(weatherRemoteDataSource: WeatherRemoteDataSource,
                                      weatherLocalDataSource: WeatherLocalDataSource): WeatherDataRepository{
        return WeatherDataRepositoryImpl(weatherRemoteDataSource, weatherLocalDataSource)
    }
}