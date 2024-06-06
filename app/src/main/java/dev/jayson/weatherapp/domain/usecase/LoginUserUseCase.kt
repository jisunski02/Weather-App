package dev.jayson.weatherapp.domain.usecase

import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class LoginUserUseCase(private val userDataRepository: UserDataRepository) {

    suspend fun invoke(username: String, password: String): Flow<UserData> {
        return userDataRepository.loginUser(username, password)
    }
}