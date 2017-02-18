package shawn.c4q.nyc.bellycheer.shawn;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import shawn.c4q.nyc.bellycheer.R;

public class TestCurrentZipActivity extends AppCompatActivity {

    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_current_zip);

        context = getApplicationContext();
        activity = TestCurrentZipActivity.this;
        requestPermission();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        testLocation();
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }


    private void testLocation(){
        CurrentLocationToZip currentLocationToZip = new CurrentLocationToZip(activity, context);
        currentLocationToZip.initGoogleClient();
    }
}
