package dev.jayson.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("gust")
    val gust: Double?
)