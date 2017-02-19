package shawn.c4q.nyc.bellycheer.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import shawn.c4q.nyc.bellycheer.R;
import shawn.c4q.nyc.bellycheer.SearchScreenActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_locate_food;
    private ImageView iv_bellycheer_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setViews();
    }

    private void setViews() {
        iv_locate_food = (ImageView) findViewById(R.id.iv_home_food_locations);
        iv_bellycheer_logo = (ImageView) findViewById(R.id.iv_home_bellycheer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_food_locations:
                goToSearchScreenActivity();
                break;
        }
    }

    private void goToSearchScreenActivity() {
        Intent intent = new Intent(this, SearchScreenActivity.class);
        this.startActivity(intent);
    }
}
