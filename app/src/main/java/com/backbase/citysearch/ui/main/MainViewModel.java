package com.backbase.citysearch.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.backbase.citysearch.async.SearchCitiesTask;
import com.backbase.citysearch.datastorage.InMemoryStore;
import com.backbase.citysearch.models.City;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<City>> searchResultsLiveData = new MutableLiveData<>();

    public void searchInitiated(String query) {
        new SearchCitiesTask(new SearchCitiesTask.SearchCitiesTaskCallback() {
            @Override
            public void onSearchCompleted(List<City> result) {
                searchResultsLiveData.setValue(result);
            }
        }, InMemoryStore.getInstance().getCityDataStore()).execute(query);
    }

    public MutableLiveData<List<City>> getSearchResultsLiveData() {
        return searchResultsLiveData;
    }
}