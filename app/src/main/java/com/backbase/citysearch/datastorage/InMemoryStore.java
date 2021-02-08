package com.backbase.citysearch.datastorage;

public class InMemoryStore {
    private final CityDataStore cityDataStore = new CityDataStoreImpl();
    private InMemoryStore() {

    }

    private static class InMemoryStoreSingleton
    {
        private static final InMemoryStore INSTANCE = new InMemoryStore();
    }

    public static InMemoryStore getInstance() {
        return InMemoryStoreSingleton.INSTANCE;
    }

    public CityDataStore getCityDataStore() {
        return cityDataStore;
    }
}
