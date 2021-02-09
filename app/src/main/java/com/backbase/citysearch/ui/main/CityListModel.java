package com.backbase.citysearch.ui.main;

import com.backbase.citysearch.async.SearchCitiesTask;
import com.backbase.citysearch.datastorage.InMemoryStore;
import com.backbase.citysearch.models.City;

import java.util.List;
import java.util.Observable;

public class CityListModel extends Observable {
    public void searchInitiated(String query) {
        new SearchCitiesTask(new SearchCitiesTask.SearchCitiesTaskCallback() {
            @Override
            public void onSearchCompleted(List<City> result) {
                setChanged();
                notifyObservers(result);
            }
        }, InMemoryStore.getInstance().getCityDataStore()).execute(query);
    }
}
