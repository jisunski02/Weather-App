package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.domain.repository.UserDataRepository

class RegisterUserUseCase(private val userDataRepository: UserDataRepository) {

    suspend fun invoke(userData: UserData){
        return userDataRepository.registerUser(userData)
    }

}