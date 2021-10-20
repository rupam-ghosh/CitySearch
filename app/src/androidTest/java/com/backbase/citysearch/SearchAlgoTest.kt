package com.backbase.citysearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.citysearch.datastorage.CityDataStore
import com.backbase.citysearch.datastorage.CityDataStoreImpl
import com.backbase.citysearch.utilities.DataUtil.addCitiesToDB
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class SearchAlgoTest {
    @Test
    fun searchWithWrongEntryShouldReturnNull() {
        val matchingCities = cityDataStore.findMatchingCities("garbage")
        Assert.assertNull(matchingCities)
    }

    @Test
    fun searchWithEmptyEntryShouldReturnAllItems() {
        val matchingCities = cityDataStore.findMatchingCities("")
        Assert.assertNotNull(matchingCities)
        Assert.assertEquals(209557, matchingCities!!.size.toLong())
    }

    @Test
    fun searchShouldReturnCorrectItems() {
        Assert.assertEquals(4, cityDataStore.findMatchingCities("Sydney")!!.size.toLong())
    }

    private val cityDataStore: CityDataStore
        private get() {
            val cityDataStore: CityDataStore = CityDataStoreImpl()
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            addCitiesToDB(appContext, cityDataStore)
            return cityDataStore
        }
}