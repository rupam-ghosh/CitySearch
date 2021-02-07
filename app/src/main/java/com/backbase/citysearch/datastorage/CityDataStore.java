package com.backbase.citysearch.datastorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.backbase.citysearch.models.City;

import java.util.List;

public interface CityDataStore {
    void addCities(@NonNull List<City> cities);

    @Nullable
    List<City> findMatchingCities(@NonNull String searchQuery);
}
