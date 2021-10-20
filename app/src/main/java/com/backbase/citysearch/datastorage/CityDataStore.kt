package com.backbase.citysearch.datastorage

import com.backbase.citysearch.models.City

interface CityDataStore {
    fun addCities(cities: List<City>)
    fun findMatchingCities(searchQuery: String?): List<City>?
}