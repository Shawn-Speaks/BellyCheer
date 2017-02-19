package shawn.c4q.nyc.bellycheer;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import backend.PantryService;
import controller.PantryAdapter;
import model.PantryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsPantryActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private static final String BASE_URL = "http://gsx2json.com/";
    private PantryAdapter adapter;
    private String TAG = "Connection result";
    private RecyclerView pantryRecyclerView;
    private TextView loadingText;
    private String zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_pantry);
        Intent intent = getIntent();
        zipCode = intent.getStringExtra("zipCode");
        loadingText = (TextView) findViewById(R.id.loading_textview);
        pantryRecyclerView = (RecyclerView) findViewById(R.id.pantry_recyclerview);
        connectToServer(BASE_URL);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


        if (googleServicsAvailable()){
            Toast.makeText(this, "perfect!!!", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
        call.enqueue(new Callback<PantryResponse>() {
            @Override
            public void onResponse(Call<PantryResponse> call, Response<PantryResponse> response) {
//              if(response.body() != (null)){
                if (response.body().getRows().size() == 0) {
                    loadingText.setText("No Sites Found in this Location.");
                }
                adapter = new PantryAdapter(response.body().getRows());
                pantryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                pantryRecyclerView.setAdapter(adapter);
            }
//            }

            @Override
            public void onFailure(Call<PantryResponse> call, Throwable t) {
                Log.d(TAG, "Failed to connect");
            }
        });
    }
}
