package com.michs.weatherapp.di

import android.content.Context
import com.michs.weatherapp.MainActivity
import com.michs.weatherapp.currentWeather.CurrentWeatherFragment
import com.michs.weatherapp.locationSearch.LocationSearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(locationSearchFragment: LocationSearchFragment)
    fun inject(currentWeatherFragment: CurrentWeatherFragment)

}