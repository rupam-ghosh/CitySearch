package com.backbase.citysearch

import com.backbase.citysearch.models.City
import com.backbase.citysearch.searchalgo.Index
import com.backbase.citysearch.searchalgo.TrieImpl
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TrieImplTest {
    @Test
    fun searchWithCorrectQueryShouldReturnSearchResult() {
        val index: Index<City> = TrieImpl()
        val cityName = "Sydney"
        val countryName = "AU"
        val testCity = City()
        testCity.country = countryName
        testCity.name = cityName
        index.addEntry(cityName, testCity)
        val searchCityResult = index.findEntries(cityName)
        Assert.assertEquals(1, searchCityResult!!.size.toLong())
        Assert.assertEquals(testCity.name, searchCityResult[0].name)
        Assert.assertEquals(testCity.country, searchCityResult[0].country)
    }

    @Test
    fun searchWithWrongQueryShouldReturnNull() {
        val index: Index<City> = TrieImpl()
        val searchCityResult = index.findEntries("Sydney")
        Assert.assertNull(searchCityResult)
    }
}