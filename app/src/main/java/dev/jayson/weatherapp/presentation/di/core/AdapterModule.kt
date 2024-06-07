package dev.jayson.weatherapp.presentation.di.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.presentation.adapter.PastWeatherAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesPastWeatherAdapter(): PastWeatherAdapter{
        return PastWeatherAdapter()
    }
}