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
        List<City> matchingCities = getCityDataStore().findMatchingCities("garbage");
        assertNull(matchingCities);
    }

    @Test
    public void searchWithEmptyEntryShouldReturnAllItems() {
        List<City> matchingCities = getCityDataStore().findMatchingCities("");
        assertNotNull(matchingCities);
        assertEquals(209557, matchingCities.size());
    }

    @Test
    public void searchShouldReturnCorrectItems() {
        assertEquals(4, getCityDataStore().findMatchingCities("Sydney").size());
    }

    private CityDataStore getCityDataStore() {
        CityDataStore cityDataStore = new CityDataStoreImpl();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataUtil.addCitiesToDB(appContext,cityDataStore);
        return cityDataStore;
    }
}