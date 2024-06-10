package dev.jayson.weatherapp.presentation.di.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dev.jayson.weatherapp.domain.usecase.GetWeatherRemoteDataUseCase
import dev.jayson.weatherapp.domain.usecase.GetWeatherRoomDataUseCase
import dev.jayson.weatherapp.domain.usecase.LoginUserUseCase
import dev.jayson.weatherapp.domain.usecase.RegisterUserUseCase
import dev.jayson.weatherapp.domain.usecase.SaveWeatherRoomDataUseCase
import dev.jayson.weatherapp.presentation.viewmodel.factory.LoginUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.RegisterUserViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherRemoteViewModelFactory
import dev.jayson.weatherapp.presentation.viewmodel.factory.WeatherRoomViewModelFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
class FactoryModule {

    @ActivityRetainedScoped
    @Provides
    fun providesLoginUserViewModelFactory(loginUserUseCase: LoginUserUseCase): LoginUserViewModelFactory{
        return LoginUserViewModelFactory(loginUserUseCase)
    }

    @ActivityRetainedScoped
    @Provides
    fun providesRegisterUseViewModelFactory(registerUserUseCase: RegisterUserUseCase): RegisterUserViewModelFactory{
        return RegisterUserViewModelFactory(registerUserUseCase)
    }

    @ActivityRetainedScoped
    @Provides
    fun providesWeatherRemoteViewModelFactory(getWeatherRemoteDataUseCase: GetWeatherRemoteDataUseCase): WeatherRemoteViewModelFactory{
        return WeatherRemoteViewModelFactory(getWeatherRemoteDataUseCase)
    }

    @ActivityRetainedScoped
    @Provides
    fun providesWeatherRoomViewModelFactory(getWeatherRoomDataUseCase: GetWeatherRoomDataUseCase, saveWeatherRoomDataUseCase: SaveWeatherRoomDataUseCase): WeatherRoomViewModelFactory{
        return WeatherRoomViewModelFactory(getWeatherRoomDataUseCase, saveWeatherRoomDataUseCase)
    }
}