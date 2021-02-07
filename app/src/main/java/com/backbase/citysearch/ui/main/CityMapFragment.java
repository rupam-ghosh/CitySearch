package com.backbase.citysearch.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.backbase.citysearch.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CityMapFragment extends Fragment {
    public static final String ARGUMENT_LATITUDE = "ARGUMENT_LATITUDE";
    public static final String ARGUMENT_LONGITUDE= "ARGUMENT_LONGITUDE";
    public static final String ARGUMENT_TITLE= "ARGUMENT_TITLE";

    public static CityMapFragment newInstance(Bundle arguments) {
        CityMapFragment cityMapFragment = new CityMapFragment();
        cityMapFragment.setArguments(arguments);
        return cityMapFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_left_20));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        MapView mapView = view.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng coordinates = new LatLng(getArguments().getDouble(ARGUMENT_LATITUDE), getArguments().getDouble(ARGUMENT_LONGITUDE));
                googleMap.addMarker(new MarkerOptions().position(coordinates).title(getArguments().getString(ARGUMENT_TITLE)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15));
                mapView.onResume();
            }
        });
    }
}
