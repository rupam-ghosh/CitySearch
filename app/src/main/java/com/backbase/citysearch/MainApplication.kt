package com.backbase.citysearch

import android.app.Application
import com.backbase.citysearch.async.AddCitiesToDBTask
import com.backbase.citysearch.datastorage.InMemoryStore

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AddCitiesToDBTask(this, InMemoryStore.cityDataStore).execute()
    }
}