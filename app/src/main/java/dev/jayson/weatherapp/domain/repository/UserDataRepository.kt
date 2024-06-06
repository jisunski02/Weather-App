package dev.jayson.weatherapp.domain.repository

import dev.jayson.weatherapp.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    suspend fun registerUser(userData: UserData)

    fun loginUser(username: String, password: String): Flow<UserData>

}