package com.example.flaglist.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET
    fun getCountriesList(): Single<List<Country>>
}