package dev.jayson.weatherapp.domain.repository

import dev.jayson.weatherapp.data.model.UserData

interface UserDataRepository {

    suspend fun registerUser(userData: UserData)

    suspend fun loginUser(username: String, password: String)

}