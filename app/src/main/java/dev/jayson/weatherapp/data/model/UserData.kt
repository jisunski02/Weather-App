package dev.jayson.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val password: String
)
