package dev.jayson.weatherapp.data.repository.datasource

import dev.jayson.weatherapp.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun registerUser(userData: UserData)

    fun loginUser(username: String, password: String): Flow<UserData>

}