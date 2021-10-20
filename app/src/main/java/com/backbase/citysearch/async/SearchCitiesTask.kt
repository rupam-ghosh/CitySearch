package com.backbase.citysearch.async

import android.os.AsyncTask
import com.backbase.citysearch.datastorage.CityDataStore
import com.backbase.citysearch.models.City

class SearchCitiesTask(private val callback: SearchCitiesTaskCallback, private val cityDataStore: CityDataStore) : AsyncTask<String?, Void?, List<City>?>() {

    override fun onPostExecute(cities: List<City>?) {
        super.onPostExecute(cities)
        callback.onSearchCompleted(cities)
    }

    interface SearchCitiesTaskCallback {
        fun onSearchCompleted(result: List<City>?)
    }

    override fun doInBackground(vararg params: String?): List<City>? {
        val query = params[0]
        return cityDataStore.findMatchingCities(query)
    }
}