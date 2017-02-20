package shawn.c4q.nyc.bellycheer;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

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

public class PantryRecyclerViewActivity extends AppCompatActivity {

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
        zipCode = intent.getStringExtra("zipCode");
        loadingText = (TextView) findViewById(R.id.loading_textview);
        pantryRecyclerView = (RecyclerView) findViewById(R.id.pantry_recyclerview);
        connectToServer(BASE_URL);
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

                    rowList = response.body().getRows();

                Log.d(TAG, rowList.size()+ "");

                if (response.body().getRows().size() == 0) {
                    loadingText.setText("No Sites Found in this Location.");
                    }
                    adapter = new PantryAdapter(rowList);
                    pantryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    pantryRecyclerView.setAdapter(adapter);

                    for(Rows row: rowList){

                        String addressAppended = row.getStreetaddress()+", "+row.getCity()+", "+row.getState();
                        row.setLatLng(convertStringAddressToLatLng(context, addressAppended));
                    }

                    for(Rows row: rowList){
                        String s = row.getLatLng().toString();
                        String t = row.getName();
                        Log.d(TAG, t + " " +" : "+ s);
                    }
            }


            @Override
            public void onFailure(Call<PantryResponse> call, Throwable t) {
                Log.d(TAG, "Failed to connect");
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
}
