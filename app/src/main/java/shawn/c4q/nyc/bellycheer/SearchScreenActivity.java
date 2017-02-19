package shawn.c4q.nyc.bellycheer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import shawn.c4q.nyc.bellycheer.shawn.sservices.CurrentLocationToZip;
import shawn.c4q.nyc.bellycheer.shawn.sservices.ZipRetrofit;

/**
 * Created by yojanasharma on 2/18/17.
 */

public class SearchScreenActivity extends AppCompatActivity implements ZipRetrofit.ZipRetrofitListener {
    private static final String TAG = "DEBUG TOOL";
    private String zipCode;
    private String siteType;
    private EditText zipEditText;
    private ImageButton currentLoc;
    private ImageButton foodPantry;
    private ImageButton soupKitchen;
    private ImageButton bothSites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen_activity);
        zipEditText = (EditText) findViewById(R.id.editext_zipcode);
        currentLoc = (ImageButton) findViewById(R.id.imagebutton_googlemap);
        bothSites = (ImageButton) findViewById(R.id.submit_button);

        currentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentLocationToZip cl = new CurrentLocationToZip(SearchScreenActivity.this, SearchScreenActivity.this);
                cl.initGoogleClient();
            }
        });

        bothSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zipEditText.getText().toString().equals("")) {
                    Toast.makeText(SearchScreenActivity.this, "Please Enter Zip Code", Toast.LENGTH_SHORT).show();
                } else {
                    zipCode = zipEditText.getText().toString();
                    Intent intent = new Intent(SearchScreenActivity.this, PantryRecyclerViewActivity.class);
                    Log.d(TAG, zipCode);
                    intent.putExtra("zipCode", zipCode);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void successfulCall(String zip) {
        zipEditText.setText(zip);
    }

}
