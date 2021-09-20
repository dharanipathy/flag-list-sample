package com.example.flaglist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flaglist.R
import com.example.flaglist.model.Country

class CountriesListAdapter(private val countriesList: ArrayList<Country>):
    RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder>() {
    fun updateCountriesList(newCountriesList: List<Country>) {
        countriesList.clear()
        countriesList.addAll(newCountriesList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.country_viewitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindViews(countriesList[position])
    }

    override fun getItemCount() = countriesList.size

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindViews(country: Country) {

    }

}


}
