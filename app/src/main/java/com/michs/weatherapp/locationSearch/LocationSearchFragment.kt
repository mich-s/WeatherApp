package com.michs.weatherapp.locationSearch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.michs.weatherapp.App
import com.michs.weatherapp.databinding.FragmentLocationSearchBinding
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import com.michs.weatherapp.repository.WeatherRepository
import javax.inject.Inject

class LocationSearchFragment: Fragment() {

    @Inject lateinit var repository: WeatherRepository
    private val searchParams = SearchParams()

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLocationSearchBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this, LocationViewModelFactory(repository)).get(LocationViewModel::class.java)

        val etCity = binding.etSearchCity
        val searchBtn = binding.btnSearch

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer<CallResult<CurrentWeatherNet>> { callResult ->
            when (callResult.status){
                CallResult.ResponseStatus.LOADING -> {
                    Log.d("callResult", "callResult status: LOADING")
                }
                CallResult.ResponseStatus.SUCCESS -> {
                    Log.d("callResult", "callResult status: SUCCESS")
                    Log.d("callResult", "${callResult.data}")
                }
                CallResult.ResponseStatus.ERROR -> {
                    Log.d("callResult", "callResult status: ERROR")
                    Log.d("callResult", "${callResult.message}")
                }
            }
        })

        searchBtn.setOnClickListener {
            searchParams.cityName = etCity.text.toString()
            viewModel.searchParams.setValue(searchParams)
        }
        return binding.root
    }
}