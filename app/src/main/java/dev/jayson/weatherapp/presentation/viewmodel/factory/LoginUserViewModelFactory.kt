package dev.jayson.weatherapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jayson.weatherapp.domain.usecase.LoginUserUseCase
import dev.jayson.weatherapp.presentation.viewmodel.LoginUserViewModel

@Suppress("UNCHECKED_CAST")
class LoginUserViewModelFactory(private val loginUserUseCase: LoginUserUseCase):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginUserViewModel(loginUserUseCase) as T
    }
}