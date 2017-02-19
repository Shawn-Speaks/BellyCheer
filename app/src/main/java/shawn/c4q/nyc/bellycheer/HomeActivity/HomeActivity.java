package shawn.c4q.nyc.bellycheer.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import shawn.c4q.nyc.bellycheer.DonateMeal.DonateMealActivity;
import shawn.c4q.nyc.bellycheer.R;
import shawn.c4q.nyc.bellycheer.SearchScreenActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton iv_locate_food;
    private ImageView iv_bellycheer_logo;
    private ImageButton ib_meal_delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setViews();
    }

    private void setViews() {
        iv_locate_food = (ImageButton) findViewById(R.id.iv_home_food_locations);
        iv_bellycheer_logo = (ImageView) findViewById(R.id.iv_home_bellycheer);
        ib_meal_delivery = (ImageButton) findViewById(R.id.iv_home_meal_delivery);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_food_locations:
                goToSearchScreenActivity();
                break;
            case R.id.iv_home_meal_delivery:
                goToDonateMealActivity();
        }
    }

    private void goToDonateMealActivity() {
        Intent intent = new Intent(this, DonateMealActivity.class);
        this.startActivity(intent);
    }

    private void goToSearchScreenActivity() {
        Intent intent = new Intent(this, SearchScreenActivity.class);
        this.startActivity(intent);
    }
}
