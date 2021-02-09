package com.backbase.citysearch;

import com.backbase.citysearch.models.City;
import com.backbase.citysearch.searchalgo.Index;
import com.backbase.citysearch.searchalgo.TrieImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TrieImplTest {
    @Test
    public void searchWithCorrectQueryShouldReturnSearchResult() {
        Index<City> index = new TrieImpl<>();
        String cityName = "Sydney";
        String countryName = "AU";
        City testCity = new City();
        testCity.setCountry(countryName);
        testCity.setName(cityName);
        index.addEntry(cityName, testCity);
        List<City> searchCityResult = index.findEntries(cityName);
        assertEquals(1, searchCityResult.size());
        assertEquals(testCity.getName(), searchCityResult.get(0).getName());
        assertEquals(testCity.getCountry(), searchCityResult.get(0).getCountry());
    }

    @Test
    public void searchWithWrongQueryShouldReturnNull() {
        Index<City> index = new TrieImpl<>();
        List<City> searchCityResult = index.findEntries("Sydney");
        assertNull(searchCityResult);
    }
}
