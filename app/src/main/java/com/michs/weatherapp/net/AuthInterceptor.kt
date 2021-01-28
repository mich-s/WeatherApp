package com.michs.weatherapp.net

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val key: String): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter("appid", key).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}