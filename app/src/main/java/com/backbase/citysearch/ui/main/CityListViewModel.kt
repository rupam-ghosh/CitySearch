package com.backbase.citysearch.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backbase.citysearch.models.City
import java.util.*

class CityListViewModel : ViewModel(), Observer {
    private val cityListModel = CityListModel()
    val cityDataMutableLiveData = MutableLiveData<CityData>()
    private var isModelObserved = false
    fun searchInitiated(query: String?) {
        if (!isModelObserved) {
            cityListModel.addObserver(this)
        }
        cityDataMutableLiveData.setValue(CityData(CityDataLoadingState.LOADING, null))
        cityListModel.searchInitiated(query)
    }

    override fun onCleared() {
        cityListModel.deleteObserver(this)
        isModelObserved = false
        super.onCleared()
    }

    override fun update(o: Observable, arg: Any) {
        val searchResults = arg as List<City>
        cityDataMutableLiveData.value = CityData(if (searchResults == null || searchResults.size < 1) CityDataLoadingState.EMPTY else CityDataLoadingState.LOADED, searchResults)
    }
}