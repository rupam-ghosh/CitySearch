package com.backbase.citysearch;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.backbase.citysearch.ui.main.CityData;
import com.backbase.citysearch.ui.main.CityDataLoadingState;
import com.backbase.citysearch.ui.main.CityListViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class CityListViewModelTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void checkMainViewModelFunctionalityForEmptySearch() throws Throwable {
        CityListViewModel cityListViewModel = new CityListViewModel();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        LifecycleOwner mockOwner = mock(LifecycleOwner.class);
        Lifecycle mockLifeCycle = mock(Lifecycle.class);
        when(mockOwner.getLifecycle()).thenReturn(mockLifeCycle);
        when(mockLifeCycle.getCurrentState()).thenReturn(Lifecycle.State.RESUMED);
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityListViewModel.getCityDataMutableLiveData().observe(mockOwner, new Observer<CityData>() {
                    @Override
                    public void onChanged(CityData cityData) {
                        long count = countDownLatch.getCount();
                        if (count == 2) {
                            assertEquals(CityDataLoadingState.LOADING, cityData.getLoadingState());
                            assertNull(cityData.getCities());
                        } else {
                            assertEquals(CityDataLoadingState.LOADED, cityData.getLoadingState());
                            assertEquals(209557, cityData.getCities().size());
                        }
                        countDownLatch.countDown();
                    }
                });
                cityListViewModel.searchInitiated("");
            }
        });
    }

    @UiThreadTest
    public void checkMainViewModelFunctionalityForWrongSearch() throws Throwable {
        CityListViewModel cityListViewModel = new CityListViewModel();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        LifecycleOwner mockOwner = mock(LifecycleOwner.class);
        Lifecycle mockLifeCycle = mock(Lifecycle.class);
        when(mockOwner.getLifecycle()).thenReturn(mockLifeCycle);
        when(mockLifeCycle.getCurrentState()).thenReturn(Lifecycle.State.RESUMED);
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityListViewModel.getCityDataMutableLiveData().observe(mock(LifecycleOwner.class), new Observer<CityData>() {
                    @Override
                    public void onChanged(CityData cityData) {
                        long count = countDownLatch.getCount();
                        if (count == 2) {
                            assertEquals(CityDataLoadingState.LOADING, cityData.getLoadingState());
                            assertNull(cityData.getCities());
                        } else {
                            assertEquals(CityDataLoadingState.EMPTY, cityData.getLoadingState());
                            assertNull(cityData.getCities());
                        }
                        countDownLatch.countDown();
                    }
                });
                cityListViewModel.searchInitiated("garbage");
            }
        });
    }
}
