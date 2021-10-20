package com.backbase.citysearch

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.backbase.citysearch.ui.main.CityDataLoadingState
import com.backbase.citysearch.ui.main.CityListViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class CityListViewModelTest {
    @Rule
    var activityTestRule: ActivityTestRule<*> = ActivityTestRule<Any?>(MainActivity::class.java)
    @Test
    @Throws(Throwable::class)
    fun checkMainViewModelFunctionalityForEmptySearch() {
        val cityListViewModel = CityListViewModel()
        val countDownLatch = CountDownLatch(2)
        val mockOwner = Mockito.mock(LifecycleOwner::class.java)
        val mockLifeCycle = Mockito.mock(Lifecycle::class.java)
        Mockito.`when`(mockOwner.lifecycle).thenReturn(mockLifeCycle)
        Mockito.`when`(mockLifeCycle.currentState).thenReturn(Lifecycle.State.RESUMED)
        activityTestRule.runOnUiThread {
            cityListViewModel.cityDataMutableLiveData.observe(mockOwner, Observer { cityData ->
                val count = countDownLatch.count
                if (count == 2L) {
                    Assert.assertEquals(CityDataLoadingState.LOADING, cityData.loadingState)
                    Assert.assertNull(cityData.cities)
                } else {
                    Assert.assertEquals(CityDataLoadingState.LOADED, cityData.loadingState)
                    Assert.assertEquals(209557, cityData.cities!!.size.toLong())
                }
                countDownLatch.countDown()
            })
            cityListViewModel.searchInitiated("")
        }
    }

    @UiThreadTest
    @Throws(Throwable::class)
    fun checkMainViewModelFunctionalityForWrongSearch() {
        val cityListViewModel = CityListViewModel()
        val countDownLatch = CountDownLatch(2)
        val mockOwner = Mockito.mock(LifecycleOwner::class.java)
        val mockLifeCycle = Mockito.mock(Lifecycle::class.java)
        Mockito.`when`(mockOwner.lifecycle).thenReturn(mockLifeCycle)
        Mockito.`when`(mockLifeCycle.currentState).thenReturn(Lifecycle.State.RESUMED)
        activityTestRule.runOnUiThread {
            cityListViewModel.cityDataMutableLiveData.observe(Mockito.mock(LifecycleOwner::class.java), Observer { cityData ->
                val count = countDownLatch.count
                if (count == 2L) {
                    Assert.assertEquals(CityDataLoadingState.LOADING, cityData.loadingState)
                    Assert.assertNull(cityData.cities)
                } else {
                    Assert.assertEquals(CityDataLoadingState.EMPTY, cityData.loadingState)
                    Assert.assertNull(cityData.cities)
                }
                countDownLatch.countDown()
            })
            cityListViewModel.searchInitiated("garbage")
        }
    }
}