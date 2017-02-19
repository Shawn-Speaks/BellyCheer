package shawn.c4q.nyc.bellycheer.HomeActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shawn.c4q.nyc.bellycheer.R;
import shawn.c4q.nyc.bellycheer.shawn.sservices.AddressToCoordRetrofit;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AddressToCoordRetrofit a = new AddressToCoordRetrofit("31-00+47th+Avenue,+Long+island+City,+NY");
    }
}
