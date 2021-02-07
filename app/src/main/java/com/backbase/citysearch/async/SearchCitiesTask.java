package com.backbase.citysearch.async;

import android.app.Application;
import android.os.AsyncTask;

import com.backbase.citysearch.MainApplication;
import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.models.City;

import java.lang.ref.WeakReference;
import java.util.List;

public class SearchCitiesTask extends AsyncTask<String, Void, List<City>> {
    private final SearchCitiesTaskCallback callback;
    private final WeakReference<Application> applicationRef;
    public SearchCitiesTask(Application application, SearchCitiesTaskCallback callback) {
        this.applicationRef = new WeakReference<>(application);
        this.callback = callback;
    }

    @Override
    protected List<City> doInBackground(String... strings) {
        String query = strings[0];
        MainApplication mainApplication = (MainApplication) applicationRef.get();
        if (mainApplication != null) {
            CityDataStore cityDataStore = mainApplication.getCityDataStore();
            return cityDataStore.findMatchingCities(query);
        } else {
            return null;
        }
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
