package shawn.c4q.nyc.bellycheer;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import backend.PantryService;
import controller.PantryAdapter;
import model.PantryResponse;
import model.Rows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PantryRecyclerViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String BASE_URL = "http://gsx2json.com/";
    private PantryAdapter adapter;
    private String TAG = "Connection result";
    private RecyclerView pantryRecyclerView;
    private TextView loadingText;
    private String zipCode;
    private List<Rows> rowList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_recycler_view);
        Intent intent = getIntent();
        context = getApplicationContext();
        Bundle bundle = getIntent().getParcelableExtra("bundle");
        zipCode = intent.getStringExtra("zipCode");
        loadingText = (TextView) findViewById(R.id.loading_textview);
        pantryRecyclerView = (RecyclerView) findViewById(R.id.pantry_recyclerview);
        connectToServer(BASE_URL);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(40.74209,-73.93551579999999)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public boolean googleServicsAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can not connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void connectToServer(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PantryService service = retrofit.create(PantryService.class);
        Call<PantryResponse> call = service.getPantries("1ITPdXilVjBOLG_rxaSxeWbK-esHrY8AX3pGvixAzDXo", "4", zipCode);
        call.enqueue(new Callback<PantryResponse>(){
            @Override
            public void onResponse(Call<PantryResponse> call, Response<PantryResponse> response) {
                if (response.body() != (null)) {
                    if (response.body().getRows().size() == 0) {
                        loadingText.setText("No Sites Found in this Location.");
                    } else {
                        loadingText.setVisibility(View.GONE);
                        rowList = response.body().getRows();
                        adapter = new PantryAdapter(rowList);
                        pantryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        pantryRecyclerView.setAdapter(adapter);

                    }
                }

                for(Rows row: rowList){
                        String addressAppended = row.getStreetaddress()+", "+row.getCity()+", "+row.getState();
                        row.setLatLng(convertStringAddressToLatLng(context, addressAppended));
                  }
              
                              updateMap(mMap, rowList);
            }

            @Override
            public void onFailure(Call<PantryResponse> call, Throwable t) {
                Log.d(TAG, "Failed to connect");
                loadingText.setText("No network connection - please try again later.");
            }
        });
    }

    private LatLng convertStringAddressToLatLng(Context context, String strAddress){
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng place = null;
        int MAX_NUM_RESULTS = 3;
        try {
            address = coder.getFromLocationName(strAddress, MAX_NUM_RESULTS);
            if(address.equals(null)){
                return null;
            }
            Address location = address.get(0);
            location.getLongitude();
            location.getLatitude();

            place = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return place;
    }

    private void updateMap(GoogleMap googleMap, List<Rows> rows){
        for(Rows row: rows){
            BitmapDescriptor bitIcon = BitmapDescriptorFactory.fromResource(R.drawable.small_soup);
            googleMap.addMarker(new MarkerOptions().position(row.getLatLng()).title(row.getName()).icon(bitIcon));
        }
    }
}
