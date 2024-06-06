package dev.jayson.weatherapp.presentation.di.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.LoginUserUseCase
import dev.jayson.weatherapp.domain.usecase.RegisterUserUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import dev.jayson.weatherapp.presentation.viewmodel.factory.LoginUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.RegisterUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesLoginUserViewModelFactory(loginUserUseCase: LoginUserUseCase): LoginUserViewModelFactory{
        return LoginUserViewModelFactory(loginUserUseCase)
    }

    @Singleton
    @Provides
    fun providesRegisterUseViewModelFactory(registerUserUseCase: RegisterUserUseCase): RegisterUserViewModelFactory{
        return RegisterUserViewModelFactory(registerUserUseCase)
    }

    @Singleton
    @Provides
    fun providesWeatherViewModelFactory(getWeatherRemoteDataUseCase: GetWeatherRemoteDataUseCase,
                                        getWeatherRoomDataUseCase: GetWeatherRoomDataUseCase,
                                        saveWeatherRoomDataUseCase: SaveWeatherRoomDataUseCase): WeatherViewModelFactory{
        return WeatherViewModelFactory(getWeatherRemoteDataUseCase, getWeatherRoomDataUseCase, saveWeatherRoomDataUseCase)
    }
}