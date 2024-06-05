package dev.jayson.weatherapp.data.repository

import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.data.repository.datasource.UserLocalDataSource
import dev.jayson.weatherapp.domain.repository.UserDataRepository

class UserDataRepositoryImpl(private val userLocalDataSource: UserLocalDataSource): UserDataRepository {

    override suspend fun registerUser(userData: UserData) {
        return userLocalDataSource.registerUser(userData)
    }

    override suspend fun loginUser(username: String, password: String) {
        return userLocalDataSource.loginUser(username, password)
    }
}