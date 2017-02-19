package com.acadgildbatch5.map;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acadgildbatch5.R;
import com.acadgildbatch5.contentproviderSample.ContentProviderExmp;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Divyanshu on 21-01-2017.
 */

public class LocationActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, ActivityCompat.OnRequestPermissionsResultCallback{


    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSSION=124;
    private static final String TAG  = "LocationActivty";
    private static final long INTERVAL = 1000 *10;
    private static final long FAST_INTERVAL = 1000 * 5 ;
    Button btnFusedLocation;
    TextView tvLocation;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;


    protected void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FAST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askForPermissions();
        setContentView(R.layout.activty_location);
        tvLocation = (TextView)findViewById(R.id.tvLocation);

        btnFusedLocation = (Button)findViewById(R.id.btnShowLocation);
        btnFusedLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateUI();
            }
        });
    }

    public void initi(){
        if(!isGooglePlayServiceAvailable()){
            finish();
        }
        createLocationRequest();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

    }

    private  boolean  isGooglePlayServiceAvailable(){
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(ConnectionResult.SUCCESS == status){
            return true;
        }else {
            GooglePlayServicesUtil.getErrorDialog(status,this,0).show();
            return  false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mGoogleApiClient.isConnected()){
            startLocationUpdate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdate();
    }

    public void stopLocationUpdate(){
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected( Bundle bundle) {

        startLocationUpdate();
    }

    protected void startLocationUpdate(){

        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }


    public void updateUI(){
        if(mCurrentLocation!= null){
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());

            tvLocation.setText("AT Time:- "+mLastUpdateTime +"\n"
            +"Longitude :- "+lng+"\n"
                    +"Latituded :- "+lat+"\n"+
                    "Accuracy :- "+mCurrentLocation.getAccuracy() + "\n"+
                    "Provide :- "+mCurrentLocation.getProvider());
        }
    }



    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    private void askForPermissions(){
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String>permissionList  =new ArrayList<String>();

        if(!addPermission(permissionList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("ACCESS FINE LOCATION");
//        if(!addPermission(permissionList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
//            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");


        if(permissionList.size()>0){
            if(permissionsNeeded.size()>0){


                String messsage = "You need to grant access to "+permissionsNeeded.get(0);
                for(int i=0; i <permissionsNeeded.size();i++){
                    messsage =messsage+" , "+permissionsNeeded.get(i);
                    requestPermissions(permissionList.toArray(new String[permissionList.size()]),REQUEST_CODE_ASK_MULTIPLE_PERMISSSION);
                    //return;
                }
                requestPermissions(permissionList.toArray(new String[permissionList.size()]),REQUEST_CODE_ASK_MULTIPLE_PERMISSSION);
                //return;
            }


        }
//        ContentProviderExmp.ListViewContactLoader listViewContactLoader = new ContentProviderExmp.ListViewContactLoader();
//        listViewContactLoader.execute();
        initi();

    }

    private boolean addPermission(List<String> permissionList, String permission){
        if(ContextCompat.checkSelfPermission(LocationActivity.this,permission)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission);
            if(!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode== REQUEST_CODE_ASK_MULTIPLE_PERMISSSION){
            Map<String,Integer> parms = new HashMap<String,Integer>();

            parms.put(Manifest.permission.ACCESS_FINE_LOCATION,PackageManager.PERMISSION_GRANTED);
//            parms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE,PackageManager.PERMISSION_GRANTED);

            for(int i = 0 ; i<permissions.length;i++){
                parms.put(permissions[i],grantResults[i]);
                if(parms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
//                    ContentProviderExmp.ListViewContactLoader ListViewContactLoader = new ContentProviderExmp.ListViewContactLoader();
//                    ListViewContactLoader.execute();
                    initi();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Some permission is denied",Toast.LENGTH_SHORT).show();
                }
            }

        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

}
