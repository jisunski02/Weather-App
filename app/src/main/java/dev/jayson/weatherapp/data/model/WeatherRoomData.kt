package dev.jayson.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_tbl")
data class WeatherRoomData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val city: String,
    val country: String,
    val weatherType: String,
    val temperature: String,
    val timeSunrise: String,
    val timeSunset: String
)