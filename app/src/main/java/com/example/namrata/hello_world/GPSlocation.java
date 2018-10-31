package com.example.namrata.hello_world;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class GPSlocation extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private TextView latituteField;
    private TextView longitudeField;
    private TextView speedField;
    LocationManager locationManager;
    String provider;
    GoogleApiClient googleApiClient;
    private double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocation);
        initViews();
        initListeners();
        initObjects();
        findLocation();
    }

    private void findLocation() {
        if (provider != null && !provider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //Requesting the Location permission
                ActivityCompat.requestPermissions(this, new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                }, 101);
                return;
            }

            locationManager.requestLocationUpdates(provider, 400, 1, this);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    if (ActivityCompat.checkSelfPermission(GPSlocation.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(GPSlocation.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(GPSlocation.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        return;
                    }

                    Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    //Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                    if (locationManager != null) {
                        if (location != null) {
                            onLocationChanged(location);
                        } else {
                           // Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
                            toMain();
                        }
                    } else {
                        //Toast.makeText(getBaseContext(), "No Location Manager Found Check Your Code", Toast.LENGTH_SHORT).show();
                        toMain();
                    }
                }
            }, 5000);

        }
    }

    private void initObjects() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use default
        Criteria criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);
        Toast.makeText(getBaseContext(), provider, Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {
        //showMap.setOnClickListener(this);
    }

    private void initViews() {
        latituteField = (TextView) findViewById(R.id.latitude_value);
        longitudeField = (TextView) findViewById(R.id.longitude_value);
        speedField = (TextView) findViewById(R.id.speed_value);
        //showMap = (Button)findViewById(R.id.button_showMap);
    }

    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
//        SupportMapFragment mapFragment = (SupportMapFragment)
//                getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Requesting the Location permission
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, 101);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location2) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        longitudeField.setText(String.valueOf(location.getLongitude()));
        longitude = location.getLongitude();
        latituteField.setText(String.valueOf(location.getLatitude()));
        latitude = location.getLatitude();
        //speedField.setText(String.valueOf(location.getSpeed()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
        toMain();
    }

    @Override
    public void onBackPressed() {
        toMain();
    }


    public void toMain() {
        Intent i = new Intent(GPSlocation.this, MainActivity.class);
        Bundle bundle = new Bundle();

//Add your data to bundle
        bundle.putString("login", "true");

//Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}

   /* Location myLocation = getLastKnownLocation();
    private Location getLastKnownLocation() {
        //locationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }*/
