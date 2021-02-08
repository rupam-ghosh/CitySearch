package com.backbase.citysearch;

import android.app.Application;

import com.backbase.citysearch.async.AddCitiesToDBTask;
import com.backbase.citysearch.datastorage.InMemoryStore;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AddCitiesToDBTask(this, InMemoryStore.getInstance().getCityDataStore()).execute();
    }
}
