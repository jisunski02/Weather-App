package dev.jayson.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.weatherapp.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LoginUserViewModel(private val loginUserUseCase: LoginUserUseCase): ViewModel() {

    fun loginUser(username: String, password: String) =  flow {
        loginUserUseCase.invoke(username, password).collect{
            emit(it)
        }
    }
}