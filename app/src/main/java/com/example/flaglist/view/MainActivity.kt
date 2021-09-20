package com.example.flaglist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flaglist.R
import com.example.flaglist.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var countriesViewModel: CountriesViewModel
    private val countriesListAdapter = CountriesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countriesViewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countriesListAdapter
        }

        countriesViewModel.refresh()

        swipeRefreshLayout.apply {
            countriesViewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        observeViewModels()
    }

    private fun observeViewModels() {
        countriesViewModel.countriesLiveData.observe(this, Observer { countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countriesListAdapter.updateCountriesList(countries)
            }
        })

        countriesViewModel.errorLiveData.observe(this, Observer { error ->
            error?.let {
                errorText.visibility = if(error) View.VISIBLE else View.GONE
            }
        })

        countriesViewModel.loadingLiveData.observe(this, Observer { loading ->
            loading?.let {
                progressBar.visibility = if(loading) View.VISIBLE else View.GONE
                if(loading) {
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                }
            }

        })
    }
}