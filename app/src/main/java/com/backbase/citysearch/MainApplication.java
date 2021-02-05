package com.backbase.citysearch;

import android.app.Application;

import com.backbase.citysearch.async.AddCitiesToDBTask;
import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.datastorage.CityDataStoreImpl;

public class MainApplication extends Application {
    public CityDataStore getCityDataStore() {
        return cityDataStore;
    }

    private CityDataStore cityDataStore;
    @Override
    public void onCreate() {
        super.onCreate();
        cityDataStore = new CityDataStoreImpl();
        new AddCitiesToDBTask(this, cityDataStore).execute();
    }
}
