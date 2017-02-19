package shawn.c4q.nyc.bellycheer.splashactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import shawn.c4q.nyc.bellycheer.R;
import shawn.c4q.nyc.bellycheer.SearchScreenActivity;

public class SplashActivity extends AppCompatActivity {

    Thread splashThread;
    ImageView iv_foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation1();
        setAnimation2();

    }

    public void setAnimation1() {
        Animation animation1 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.anim_slide_left);
        ImageView bellycheer = (ImageView) findViewById(R.id.iv_bellycheer_logo);
        bellycheer.startAnimation(animation1);
    }

    public void setAnimation2() {
        Animation animation2 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.anim_move_up);
        ImageView foods = (ImageView) findViewById(R.id.iv_bellycheer_foods);
        foods.setAnimation(animation2);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SearchScreenActivity.class));
                finish();
            }
        }, 6000);


    }

}

