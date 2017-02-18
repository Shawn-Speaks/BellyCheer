package shawn.c4q.nyc.bellycheer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import backend.PantryService;
import controller.PantryAdapter;
import model.PantryResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_recycler_view);
        pantryRecyclerView = (RecyclerView) findViewById(R.id.pantry_recyclerview);
        connectToServer(BASE_URL);
    }

    private void connectToServer(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PantryService service = retrofit.create(PantryService.class);
        Call<PantryResponse> call = service.getPantries("1ITPdXilVjBOLG_rxaSxeWbK-esHrY8AX3pGvixAzDXo", "4", "");
        call.enqueue(new Callback<PantryResponse>() {
            @Override
            public void onResponse(Call<PantryResponse> call, Response<PantryResponse> response) {
                adapter = new PantryAdapter(response.body().getRows());
                pantryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                pantryRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PantryResponse> call, Throwable t) {
                Log.d(TAG, "Failed to connect");
            }
        });
    }
}
