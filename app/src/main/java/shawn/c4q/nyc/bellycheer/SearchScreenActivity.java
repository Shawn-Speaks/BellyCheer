package shawn.c4q.nyc.bellycheer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import shawn.c4q.nyc.bellycheer.HomeActivity.HomeActivity;

/**
 * Created by yojanasharma on 2/18/17.
 */

public class SearchScreenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Spinner spinner_borough;
    private ImageView iv_bellycheer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen_activity);
        setViews();
        setSpinner();
    }

    public void setViews(){
        iv_bellycheer = (ImageView) findViewById(R.id.iv_search_bellycheer_logo);
        iv_bellycheer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeActivity();
            }
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }


    public void setSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner_borough);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.boroughs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner_borough = (Spinner) findViewById(R.id.spinner_borough);
        spinner_borough.setOnItemSelectedListener(this);
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
