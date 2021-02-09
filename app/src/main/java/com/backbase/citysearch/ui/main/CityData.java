package com.backbase.citysearch.ui.main;

import com.backbase.citysearch.models.City;

import java.util.List;

public class CityData {
    public CityData(CityDataLoadingState loadingState, List<City> cities) {
        this.loadingState = loadingState;
        this.cities = cities;
    }

    public CityDataLoadingState getLoadingState() {
        return loadingState;
    }

    public List<City> getCities() {
        return cities;
    }

    private CityDataLoadingState loadingState;
    private List<City> cities;
}
