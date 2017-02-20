package shawn.c4q.nyc.bellycheer.shawn.sservices;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import shawn.c4q.nyc.bellycheer.SearchScreenActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class CurrentLocationToZip implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleClient;
    private SearchScreenActivity activity;
    private Context context;


    public CurrentLocationToZip(SearchScreenActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    public void initGoogleClient(){
        mGoogleClient = new GoogleApiClient.Builder(activity)
                            .addConnectionCallbacks(this)
                            .addOnConnectionFailedListener(this)
                            .addApi(LocationServices.API)
                            .build();

        mGoogleClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "App may not behave properly", Toast.LENGTH_SHORT).show();
            return;
        }

        Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);
        Log.d(TAG, String.valueOf(mCurrentLocation.getLatitude()));
        Log.d(TAG, String.valueOf(mCurrentLocation.getLongitude()));
        new ZipRetrofit(mCurrentLocation, activity);
        mGoogleClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
