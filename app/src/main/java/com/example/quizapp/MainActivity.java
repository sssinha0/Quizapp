package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    //variable
    Animation topani,bottomani;
    ImageView image;
    TextView play,power;
   // ConstraintLayout layout;
    //splash screen timing
    final int Splash_screen_time=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  layout=findViewById(R.id.splash_main);
        //animaions
      topani= AnimationUtils.loadAnimation(this,R.anim.top_animation);
    //  layout.setAnimation(topani);
        bottomani=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //initialize
        image=findViewById(R.id.imageView2);
        image.setAnimation(topani);
        play=findViewById(R.id.textView);
        play.setAnimation(bottomani);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
                finish();

            }
        },Splash_screen_time);
    }
}