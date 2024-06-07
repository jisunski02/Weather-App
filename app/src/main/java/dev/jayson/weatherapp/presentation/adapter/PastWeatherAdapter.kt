package dev.jayson.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jayson.weatherapp.R
import dev.jayson.weatherapp.data.model.WeatherRoomData
import dev.jayson.weatherapp.databinding.LayoutPastweatherBinding

class PastWeatherAdapter: RecyclerView.Adapter<PastWeatherAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<WeatherRoomData>(){
        override fun areItemsTheSame(oldItem: WeatherRoomData, newItem: WeatherRoomData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherRoomData, newItem: WeatherRoomData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PastWeatherAdapter.ViewHolder {
        val binding = LayoutPastweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PastWeatherAdapter.ViewHolder, position: Int) {
        val weatherRoomData = differ.currentList[position]
        holder.bind(weatherRoomData)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: LayoutPastweatherBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(weatherRoomData: WeatherRoomData){
            binding.tvLocation.text = "${weatherRoomData.city}, ${weatherRoomData.country}"
            binding.tvTemperature.text = weatherRoomData.temperature
            binding.tvSunrise.text = weatherRoomData.timeSunrise
            binding.tvSunset.text = weatherRoomData.timeSunset

            if(weatherRoomData.weatherType.contains("Clouds")){
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_cloudy)
            }

            if(weatherRoomData.weatherType.contains("Rain")){
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_rainy)
            }

            if(weatherRoomData.weatherType == "Sun"){
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_sunny)
            }
        }
    }
}