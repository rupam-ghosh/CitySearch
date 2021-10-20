package com.backbase.citysearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.backbase.citysearch.ui.main.CityListFragment
import com.backbase.citysearch.ui.main.CityListFragment.Companion.newInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, newInstance())
                    .addToBackStack(CityListFragment::class.java.name)
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}