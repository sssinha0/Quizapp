package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //variable
    Animation topani,bottomani;
    ImageView image;
    TextView play,power;
    //splash screen timing
    final int Splash_screen_time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //animaions
        topani= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomani=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //initialize
        image=findViewById(R.id.imageView2);
        play=findViewById(R.id.textView);
        power=findViewById(R.id.textView2);

        image.setAnimation(topani);
        play.setAnimation(bottomani);
        power.setAnimation(bottomani);

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