package com.backbase.citysearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.citysearch.datastorage.InMemoryStore
import com.backbase.citysearch.models.City
import com.backbase.citysearch.ui.main.CityListModel
import com.backbase.citysearch.utilities.DataUtil.addCitiesToDB
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class CityListModelTest {
    @Test
    fun checkMainModelFunctionality() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        addCitiesToDB(appContext, InMemoryStore.getInstance().getCityDataStore())
        val cityListModel = CityListModel()
        val countDownLatch = CountDownLatch(1)
        cityListModel.addObserver { o, arg ->
            val cityList = arg as List<City>
            Assert.assertEquals(209557, cityList.size.toLong())
            countDownLatch.countDown()
        }
        cityListModel.searchInitiated("")
    }
}