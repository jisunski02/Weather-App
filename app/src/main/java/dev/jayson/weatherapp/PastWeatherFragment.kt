package dev.jayson.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jayson.weatherapp.databinding.FragmentPastWeatherBinding
import dev.jayson.weatherapp.presentation.adapter.PastWeatherAdapter
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRemoteViewModel
import dev.jayson.weatherapp.presentation.viewmodel.WeatherRoomViewModel
import kotlinx.coroutines.launch


class PastWeatherFragment : Fragment() {

    private lateinit var binding: FragmentPastWeatherBinding
    private lateinit var weatherRoomViewModel: WeatherRoomViewModel
    private lateinit var pastWeatherAdapter: PastWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPastWeatherBinding.bind(view)
        weatherRoomViewModel = (activity as WeatherActivity).weatherRoomViewModel

        pastWeatherAdapter = (activity as WeatherActivity).pastWeatherAdapter

        getSavedWeatherData()
    }

    private fun getSavedWeatherData(){
        binding.rvWeather.apply {
            adapter = pastWeatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            // Repeat this coroutine as long as the lifecycle is in the CREATED state
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {

                weatherRoomViewModel.getWeatherRoomData().collect{
                    pastWeatherAdapter.differ.submitList(it)
                }
            }

            }
    }


}