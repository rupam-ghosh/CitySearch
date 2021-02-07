package com.backbase.citysearch.utilities;

import android.content.Context;

import androidx.annotation.Nullable;

import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.models.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class DataUtil {
    public static void addCitiesToDB(Context context, CityDataStore cityDataStore) {
        List<City> cities = readCitiesJSONFileFromAssets(context);
        if (cities != null) {
            cityDataStore.addCities(cities);
        }
    }

    private static @Nullable
    List<City> readCitiesJSONFileFromAssets(Context context) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("cities.json")));
            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
