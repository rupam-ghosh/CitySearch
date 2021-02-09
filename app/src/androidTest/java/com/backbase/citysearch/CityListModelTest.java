package com.backbase.citysearch;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.backbase.citysearch.datastorage.InMemoryStore;
import com.backbase.citysearch.models.City;
import com.backbase.citysearch.ui.main.CityListModel;
import com.backbase.citysearch.utilities.DataUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CityListModelTest {
    @Test
    public void checkMainModelFunctionality() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataUtil.addCitiesToDB(appContext, InMemoryStore.getInstance().getCityDataStore());
        CityListModel cityListModel = new CityListModel();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        cityListModel.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                List<City> cityList = (List<City>) arg;
                assertEquals(209557, cityList.size());
                countDownLatch.countDown();
            }
        });
        cityListModel.searchInitiated("");
    }
}
