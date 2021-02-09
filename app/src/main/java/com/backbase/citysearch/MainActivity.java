package com.backbase.citysearch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.backbase.citysearch.ui.main.CityListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CityListFragment.newInstance())
                    .addToBackStack(CityListFragment.class.getName())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}