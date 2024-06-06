package dev.jayson.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jayson.weatherapp.data.model.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(userData: UserData)

    @Query("SELECT * FROM user_tbl WHERE username=:username AND password=:password LIMIT 1")
    fun loginUser(username: String, password: String): Flow<UserData>
}