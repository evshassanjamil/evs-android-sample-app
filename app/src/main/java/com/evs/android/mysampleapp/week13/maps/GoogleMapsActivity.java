package com.evs.android.mysampleapp.week13.maps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng evs = new LatLng(33.635536, 73.073998);
        googleMap.addMarker(new MarkerOptions().position(evs).title("EVS Training Institute, Rawalpindi Campus"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(evs, 18));

        //plotRandom(googleMap, 10);
    }

    /*private void plotRandom(GoogleMap map, int number) {
        LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
        LatLng southWest = bounds.southwest;
        LatLng northEast = bounds.northeast;
        double lngSpan = northEast.longitude - southWest.longitude;
        double latSpan = northEast.latitude - southWest.latitude;
        LatLng[] pointsrand = new LatLng[number];

        for (int i = 0; i < number; ++i) {
            LatLng point = new LatLng(southWest.latitude + latSpan * Math.random(),
                    southWest.longitude + lngSpan * Math.random());
            pointsrand[i] = point;
        }

        for (int i = 0; i < number; ++i) {
            String markerText = i + " : " + pointsrand[i];
            map.addMarker(new MarkerOptions()
                    .position(pointsrand[i])
                    .title(markerText));
            //arrMarkers.push(marker);
        }

    }*/
}
