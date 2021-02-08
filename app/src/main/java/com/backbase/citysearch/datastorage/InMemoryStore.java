package com.backbase.citysearch.datastorage;

public class InMemoryStore {
    private CityDataStore cityDataStore = new CityDataStoreImpl();
    private static InMemoryStore inMemoryStore;
    public static InMemoryStore getInstance() {
        if(inMemoryStore == null) {
            inMemoryStore = new InMemoryStore();
        }
        return inMemoryStore;
    }

    public CityDataStore getCityDataStore() {
        return cityDataStore;
    }
}
