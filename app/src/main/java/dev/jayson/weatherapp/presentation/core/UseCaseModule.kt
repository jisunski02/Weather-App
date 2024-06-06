package dev.jayson.weatherapp.presentation.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jayson.weatherapp.domain.repository.UserDataRepository
import dev.jayson.weatherapp.domain.repository.WeatherDataRepository
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.LoginUserUseCase
import dev.jayson.weatherapp.domain.usecase.RegisterUserUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetWeatherRoomDataUseCase(weatherDataRepository: WeatherDataRepository): GetWeatherRoomDataUseCase{
        return GetWeatherRoomDataUseCase(weatherDataRepository)
    }

    @Singleton
    @Provides
    fun providesGetWeatherRemoteDataUseCase(weatherDataRepository: WeatherDataRepository): GetWeatherRemoteDataUseCase{
        return GetWeatherRemoteDataUseCase(weatherDataRepository)
    }

    @Singleton
    @Provides
    fun providesLoginUserUseCase(userDataRepository: UserDataRepository): LoginUserUseCase{
        return LoginUserUseCase(userDataRepository)
    }

    @Singleton
    @Provides
    fun providesRegisterUserUseCase(userDataRepository: UserDataRepository): RegisterUserUseCase{
        return RegisterUserUseCase(userDataRepository)
    }

    @Singleton
    @Provides
    fun providesSaveWeatherRoomDataUseCase(weatherDataRepository: WeatherDataRepository): SaveWeatherRoomDataUseCase{
        return SaveWeatherRoomDataUseCase(weatherDataRepository)
    }
}