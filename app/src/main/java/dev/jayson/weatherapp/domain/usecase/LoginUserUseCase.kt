package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.domain.repository.UserDataRepository

class LoginUserUseCase(private val userDataRepository: UserDataRepository) {

    suspend fun invoke(username: String, password: String){
        return userDataRepository.loginUser(username, password)
    }
}