package pl.aprilapps.motiondetectorsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//        long delayInMillis = 2000;
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent i = new Intent(Splash.this,Login.class);
//                startActivity(i);
//            }
//        }, delayInMillis);



        final ImageView imageView = (ImageView) findViewById(R.id.splash);
        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.alpha);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.translate);
        final Animation animation_3 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imageView.startAnimation(animation_2);
        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_3);
                finish();
                Intent i = new Intent(getBaseContext(), Home.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}