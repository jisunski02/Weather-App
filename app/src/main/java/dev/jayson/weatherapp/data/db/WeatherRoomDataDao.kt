package dev.jayson.weatherapp.data.db

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jayson.weatherapp.data.model.WeatherRoomData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherRoomDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherRoomData(weatherRoomData: WeatherRoomData)

    @Query("SELECT * FROM weather_tbl")
    fun getWeatherRoomData(): Flow<WeatherRoomData>

    @Query("DELETE FROM weather_tbl")
    suspend fun deleteAllWeatherRoomData()

}