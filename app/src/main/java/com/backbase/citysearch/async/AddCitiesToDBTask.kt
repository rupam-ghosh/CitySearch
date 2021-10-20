package com.backbase.citysearch.async

import android.content.Context
import android.os.AsyncTask
import com.backbase.citysearch.datastorage.CityDataStore
import com.backbase.citysearch.utilities.DataUtil
import java.lang.ref.WeakReference

class AddCitiesToDBTask(context: Context?, cityDataStore: CityDataStore) : AsyncTask<Void?, Void?, Void?>() {
    private val contextRef: WeakReference<Context?>
    private val cityDataStore: CityDataStore

    init {
        contextRef = WeakReference(context)
        this.cityDataStore = cityDataStore
    }

    override fun doInBackground(vararg params: Void?): Void? {
        if (contextRef.get() != null) {
            DataUtil.addCitiesToDB(contextRef.get(), cityDataStore)
        }
        return null
    }
}