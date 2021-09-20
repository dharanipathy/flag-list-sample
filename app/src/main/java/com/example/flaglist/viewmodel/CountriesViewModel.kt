package com.example.flaglist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flaglist.model.CountriesService
import com.example.flaglist.model.Country
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CountriesViewModel: ViewModel() {
    val countriesLiveData = MutableLiveData<List<Country>>()
    val errorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        loadingLiveData.value = true
        disposable.add(countriesService
            .getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(countries: List<Country>?) {
                    countries?.let {
                        countriesLiveData.value = countries
                        loadingLiveData.value = false
                        errorLiveData.value = false
                    }

                }

                override fun onError(error: Throwable?) {
                    error?.let {
                        loadingLiveData.value = false
                        errorLiveData.value = true
                    }
                }

            })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}