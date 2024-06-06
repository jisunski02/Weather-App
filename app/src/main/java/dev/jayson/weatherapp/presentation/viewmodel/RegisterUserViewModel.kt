package dev.jayson.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.launch

class RegisterUserViewModel(private val registerUserUseCase: RegisterUserUseCase): ViewModel() {

    suspend fun registerUser(userData: UserData) = viewModelScope.launch {
        registerUserUseCase.invoke(userData)
    }
}