package com.backbase.citysearch.async;

import android.content.Context;
import android.os.AsyncTask;

import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.utilities.DataUtil;

import java.lang.ref.WeakReference;

public class AddCitiesToDBTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<Context> contextRef;
    private final CityDataStore cityDataStore;

    public AddCitiesToDBTask(Context context, CityDataStore cityDataStore) {
        this.contextRef = new WeakReference<>(context);
        this.cityDataStore = cityDataStore;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (this.contextRef.get() != null) {
            DataUtil.addCitiesToDB(this.contextRef.get(), this.cityDataStore);
        }
        return null;
    }
}
