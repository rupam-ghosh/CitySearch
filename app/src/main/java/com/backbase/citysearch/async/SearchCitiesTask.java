package com.backbase.citysearch.async;

import android.os.AsyncTask;

import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.models.City;
import java.util.List;

public class SearchCitiesTask extends AsyncTask<String, Void, List<City>> {
    private SearchCitiesTaskCallback callback;
    private CityDataStore cityDataStore;

    public SearchCitiesTask(SearchCitiesTaskCallback callback, CityDataStore cityDataStore) {
        this.callback = callback;
        this.cityDataStore = cityDataStore;
    }

    @Override
    protected List<City> doInBackground(String... strings) {
        String query = strings[0];
        return cityDataStore.findMatchingCities(query);
    }

    @Override
    protected void onPostExecute(List<City> cities) {
        super.onPostExecute(cities);
        callback.onSearchCompleted(cities);
    }

    public interface SearchCitiesTaskCallback {
        void onSearchCompleted(List<City> result);
    }
}
