package com.backbase.citysearch.datastorage;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.backbase.citysearch.models.City;
import com.backbase.citysearch.searchalgo.Index;
import com.backbase.citysearch.searchalgo.TrieImpl;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class CityDataStoreImpl implements CityDataStore {
    private Index<City> trie;
    private List<City> sortedCityList;
    public CityDataStoreImpl() {
        trie = new TrieImpl<City>();
    }

    @Override
    public void addCities(@NonNull List<City> cities) {
        for(City city: cities) {
            trie.addEntry(city.getName().toLowerCase() + ", " + city.getCountry().toLowerCase(), city);
        }
        Collections.sort(cities);
        sortedCityList = cities;
    }

    @Override
    public @Nullable
    List<City> findMatchingCities(@NotNull String searchQuery) {
        if(TextUtils.isEmpty(searchQuery)) {
            return sortedCityList;
        }
        List<City> cities = trie.findEntries(searchQuery.toLowerCase());
        if(cities != null) {
            Collections.sort(cities);
        }
        return cities;
    }
}
