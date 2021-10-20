package com.backbase.citysearch.ui.main

import com.backbase.citysearch.async.SearchCitiesTask
import com.backbase.citysearch.async.SearchCitiesTask.SearchCitiesTaskCallback
import com.backbase.citysearch.datastorage.InMemoryStore
import com.backbase.citysearch.models.City
import java.util.*

class CityListModel : Observable() {
    fun searchInitiated(query: String?) {
        SearchCitiesTask(object : SearchCitiesTaskCallback {
            override fun onSearchCompleted(result: List<City>?) {
                setChanged()
                notifyObservers(result)
            }
        }, InMemoryStore.cityDataStore).execute(query)
    }
}