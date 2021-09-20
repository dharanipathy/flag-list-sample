package com.example.flaglist.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    companion object {
        private const val baseUrl = "https://raw.githubusercontent.com"
    }
    private val service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    fun getCountriesList() = service.getCountriesList()
}