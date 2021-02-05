package com.backbase.citysearch;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.backbase.citysearch.datastorage.CityDataStore;
import com.backbase.citysearch.datastorage.CityDataStoreImpl;
import com.backbase.citysearch.models.City;
import com.backbase.citysearch.utilities.DataUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SearchAlgoTest {
    @Test
    public void searchWithWrongEntryShouldReturnNull() {
        CityDataStore cityDataStore = new CityDataStoreImpl();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataUtil.addCitiesToDB(appContext,cityDataStore);
        List<City> matchingCities = cityDataStore.findMatchingCities("kjabfkabfkabfkaf");
        assertNull(matchingCities);
    }

    @Test
    public void searchWithEmptyEntryShouldReturnAllItems() {
        CityDataStore cityDataStore = new CityDataStoreImpl();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataUtil.addCitiesToDB(appContext,cityDataStore);
        List<City> matchingCities = cityDataStore.findMatchingCities("");
        assertNotNull(matchingCities);
        assertEquals(209557, matchingCities.size());
    }

    @Test
    public void searchShouldReturnCorrectItems() {
        CityDataStore cityDataStore = new CityDataStoreImpl();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataUtil.addCitiesToDB(appContext,cityDataStore);

        assertEquals(4, cityDataStore.findMatchingCities("Sydney").size());
        assertEquals(221, cityDataStore.findMatchingCities("Alb").size());
    }
}