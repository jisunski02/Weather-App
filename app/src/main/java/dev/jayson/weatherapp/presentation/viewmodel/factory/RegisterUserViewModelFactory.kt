package dev.jayson.weatherapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jayson.weatherapp.domain.usecase.RegisterUserUseCase
import dev.jayson.weatherapp.presentation.viewmodel.RegisterUserViewModel

@Suppress("UNCHECKED_CAST")
class RegisterUserViewModelFactory(private val registerUserUseCase: RegisterUserUseCase):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterUserViewModel(registerUserUseCase) as T
    }
}