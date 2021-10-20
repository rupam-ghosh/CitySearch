package com.backbase.citysearch.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.backbase.citysearch.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CityMapFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.map_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.baseline_keyboard_arrow_left_20)
        toolbar.setNavigationOnClickListener { activity!!.supportFragmentManager.popBackStackImmediate() }
        val mapView: MapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            val coordinates = LatLng(arguments!!.getDouble(ARGUMENT_LATITUDE), arguments!!.getDouble(ARGUMENT_LONGITUDE))
            googleMap.addMarker(MarkerOptions().position(coordinates).title(arguments!!.getString(ARGUMENT_TITLE)))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f))
            mapView.onResume()
        }
    }

    companion object {
        const val ARGUMENT_LATITUDE = "ARGUMENT_LATITUDE"
        const val ARGUMENT_LONGITUDE = "ARGUMENT_LONGITUDE"
        const val ARGUMENT_TITLE = "ARGUMENT_TITLE"
        fun newInstance(arguments: Bundle?): CityMapFragment {
            val cityMapFragment = CityMapFragment()
            cityMapFragment.arguments = arguments
            return cityMapFragment
        }
    }
}