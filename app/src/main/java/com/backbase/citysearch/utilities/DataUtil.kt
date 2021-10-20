package com.backbase.citysearch.utilities

import android.content.Context
import com.backbase.citysearch.datastorage.CityDataStore
import com.backbase.citysearch.models.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object DataUtil {
    @JvmStatic
    fun addCitiesToDB(context: Context?, cityDataStore: CityDataStore) {
        context?.apply {
            val cities = readCitiesJSONFileFromAssets(context)
            if (cities != null) {
                cityDataStore.addCities(cities)
            }
        }
    }

    private fun readCitiesJSONFileFromAssets(context: Context): List<City>? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(
                    InputStreamReader(context.assets.open("cities.json")))
            val gson = Gson()
            val listType = object : TypeToken<List<City>?>() {}.type
            return gson.fromJson(reader, listType)
        } catch (e: IOException) {
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                }
            }
        }
        return null
    }
}