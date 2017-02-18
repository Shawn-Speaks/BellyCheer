package shawn.c4q.nyc.bellycheer.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import shawn.c4q.nyc.bellycheer.MainActivity;
import shawn.c4q.nyc.bellycheer.R;

public class SplashActivity extends AppCompatActivity {

    Thread splashThread;
    ImageView iv_foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();


    }

    public void setAnimation() {
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.anim_move_up);
        ImageView iv = (ImageView) findViewById(R.id.iv_bellycheer_foods);
        iv.setAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 6000);

    }


}

