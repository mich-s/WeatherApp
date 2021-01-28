package com.michs.weatherapp

import android.app.Application
import com.michs.weatherapp.di.AppComponent
import com.michs.weatherapp.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}