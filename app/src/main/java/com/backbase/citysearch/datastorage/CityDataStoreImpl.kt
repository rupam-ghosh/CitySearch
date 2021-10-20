package com.backbase.citysearch.datastorage

import android.text.TextUtils
import com.backbase.citysearch.models.City
import com.backbase.citysearch.searchalgo.Index
import com.backbase.citysearch.searchalgo.TrieImpl
import java.util.*

class CityDataStoreImpl : CityDataStore {
    private val trie: Index<City>
    private var sortedCityList: List<City>? = null

    override fun addCities(cities: List<City>) {
        for (city in cities) {
            trie.addEntry(city.name?.toLowerCase() + ", " + city.country?.toLowerCase(), city)
        }
        Collections.sort(cities)
        sortedCityList = cities
    }

    override fun findMatchingCities(searchQuery: String?): List<City>? {
        searchQuery?.apply {
            if (TextUtils.isEmpty(searchQuery)) {
                return sortedCityList
            }
            val cities = trie.findEntries(searchQuery.toLowerCase())
            cities?.apply {
                Collections.sort(cities)
            }
            return cities
        }
        return null;
    }

    init {
        trie = TrieImpl()
    }
}