package com.michs.weatherapp.locationSearch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.michs.weatherapp.App
import com.michs.weatherapp.R
import com.michs.weatherapp.databinding.FragmentLocationSearchBinding
import com.michs.weatherapp.domain.Coordinates
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import com.michs.weatherapp.repository.WeatherRepository
import com.michs.weatherapp.viewModel.WeatherViewModel
import com.michs.weatherapp.viewModel.WeatherViewModelFactory
import javax.inject.Inject

class LocationSearchFragment: Fragment() {

    @Inject lateinit var repository: WeatherRepository
    private val searchParams = SearchParams()
    private val viewModel: WeatherViewModel by activityViewModels { WeatherViewModelFactory(repository)}

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
        binding.lifecycleOwner = viewLifecycleOwner

        val etCity = binding.etSearchCityName
        val etLatitude = binding.etSearchLatitude
        val etLongitude = binding.etSearchLongitude
        val searchBtn = binding.btnSearch
        val rbCityName = binding.rbCityName
        val rbCoordinates = binding.rbCoordinates
        val loadingLayout = binding.loadingLayout
        var isSearchBtnClicked = false

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer<CallResult<CurrentWeatherNet>> { callResult ->
            when (callResult.status){
                CallResult.ResponseStatus.LOADING -> {
                    loadingLayout.isVisible = true
                    Log.d("callResult", "callResult status: LOADING")
                }
                CallResult.ResponseStatus.SUCCESS -> {
                    loadingLayout.isVisible = false
                    Log.d("callResult", "SUCCESS: ${callResult.data}")
                    if(isSearchBtnClicked) {
                        isSearchBtnClicked = false
                        val direction =
                            LocationSearchFragmentDirections.actionLocationSearchFragmentToCurrentWeatherFragment()
                        findNavController().navigate(direction)
                    }
                }
                CallResult.ResponseStatus.ERROR -> {
                    loadingLayout.isVisible = false
                    Log.d("callResult", "ERROR: ${callResult.message}")
                    Toast.makeText(activity, "${callResult.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })


        rbCityName.setOnCheckedChangeListener{
            _, isChecked ->
            if(isChecked){
                etCity.visibility = View.VISIBLE
                etLatitude.visibility = View.GONE
                etLongitude.visibility = View.GONE
                etLatitude.text = null
                etLongitude.text = null
            }
        }
        rbCoordinates.setOnCheckedChangeListener{
            _, isChecked ->
            if(isChecked){
                etCity.visibility = View.GONE
                etLatitude.visibility = View.VISIBLE
                etLongitude.visibility = View.VISIBLE
                etCity.text = null
            }
        }

        searchBtn.setOnClickListener {
            searchParams.cityName = null
            searchParams.coordinates = null
            if(rbCityName.isChecked) {
                if(!isValidCityInput(etCity))
                    return@setOnClickListener
                searchParams.cityName = etCity.text.toString()
            }
            else {
                if(!isValidCoordinatesInput(etLatitude, etLongitude))
                    return@setOnClickListener
                searchParams.coordinates = Coordinates(
                    etLatitude.text.toString().trim().toDouble(),
                    etLongitude.text.toString().trim().toDouble()
                )
            }
            viewModel.searchParams.value = searchParams
            isSearchBtnClicked = true
        }
        return binding.root
    }

    private fun isValidCityInput(etCityName: EditText): Boolean{
        if(etCityName.text.toString().isBlank()){
            etCityName.error = "Must contain at least 1 character"
            return false
        }
        return true
    }

    private fun isValidCoordinatesInput(etLatitude: EditText, etLongitude: EditText): Boolean{
        var isValid = true
        val latitude = etLatitude.text.toString()
        if(latitude.isBlank() || latitude.trim() == "." || !latitude.matches(".*\\d.*".toRegex())){
            etLatitude.error = resources.getString(R.string.lat_digit_validation)
            isValid = false
        }
        else if (latitude.toDouble() !in -90.0..90.0){
            etLatitude.error = resources.getString(R.string.lat_range_validation)
            isValid = false
        }
        val longitude = etLongitude.text.toString()
        if(longitude.isBlank() || longitude.trim() == "." || !longitude.matches(".*\\d.*".toRegex())){
            etLongitude.error = resources.getString(R.string.lon_digit_validation)
            isValid = false
        }
        else if (longitude.toDouble() !in -180.0..180.0){
            etLongitude.error = resources.getString(R.string.lon_range_validation)
            isValid = false
        }
        return isValid
    }
}