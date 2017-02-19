package shawn.c4q.nyc.bellycheer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

/**
 * Created by yojanasharma on 2/18/17.
 */

public class SearchScreenActivity extends AppCompatActivity {
    private String zipCode;
    private String siteType;
    ImageButton foodPantry;
    ImageButton soupKitchen;
    ImageButton bothSites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen_activity);

    }
}
