package dev.jayson.weatherapp.data.repository.datasourceimpl

import dev.jayson.weatherapp.data.db.UserDataDao
import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.data.repository.datasource.UserLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLocalDataSourceImpl(private val userDataDao: UserDataDao): UserLocalDataSource {

    override suspend fun registerUser(userData: UserData) {
        CoroutineScope(Dispatchers.IO).launch {
            userDataDao.registerUser(userData)
        }
    }

    override suspend fun loginUser(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userDataDao.loginUser(username, password)
        }
    }
}