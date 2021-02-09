package com.backbase.citysearch.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.backbase.citysearch.models.City;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CityListViewModel extends ViewModel implements Observer {
    private final CityListModel cityListModel = new CityListModel();
    private final MutableLiveData<CityData> cityDataMutableLiveData = new MutableLiveData<>();
    private boolean isModelObserved;

    public void searchInitiated(String query) {
        if(!isModelObserved) {
            cityListModel.addObserver(this);
        }
        cityDataMutableLiveData.setValue(new CityData(CityDataLoadingState.LOADING, null));
        cityListModel.searchInitiated(query);
    }

    public MutableLiveData<CityData> getCityDataMutableLiveData() {
        return cityDataMutableLiveData;
    }

    @Override
    protected void onCleared() {
        cityListModel.deleteObserver(this);
        isModelObserved = false;
        super.onCleared();
    }

    @Override
    public void update(Observable o, Object arg) {
        List<City> searchResults = (List<City>)arg;
        cityDataMutableLiveData.setValue(
                new CityData(searchResults == null || searchResults.size() < 1 ? CityDataLoadingState.EMPTY: CityDataLoadingState.LOADED, searchResults));
    }
}