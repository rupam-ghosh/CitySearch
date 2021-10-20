package com.backbase.citysearch.datastorage

object InMemoryStore {
    val cityDataStore: CityDataStore = CityDataStoreImpl()
}