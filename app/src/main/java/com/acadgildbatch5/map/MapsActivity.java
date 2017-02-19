package com.acadgildbatch5.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.acadgildbatch5.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(12.9716, 77.5946);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Banglore"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        // latitude and longitude
        double latitude =12.9716 ;
        double longitude = 77.5946;
// create marker
        MarkerOptions marker = new MarkerOptions() .position(new LatLng(latitude, longitude)) .title("Hello Maps ");
// adding marker
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.greencheck));

        googleMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder() .target(new LatLng(latitude, longitude)) .zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory .newCameraPosition(cameraPosition));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }


}
