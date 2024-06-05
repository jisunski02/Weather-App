package dev.jayson.weatherapp.data.repository.datasource

import dev.jayson.weatherapp.data.model.UserData

interface UserLocalDataSource {

    suspend fun registerUser(userData: UserData)

    suspend fun loginUser(username: String, password: String)

}