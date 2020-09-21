package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Quiz_play extends AppCompatActivity {
    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_play);
        circleImageView=findViewById(R.id.profile_des);
        Glide.with(Quiz_play.this).load(getIntent().getStringExtra("img_url").toString()).into(circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Quiz_play.this,profile_detail.class);
                intent.putExtra("f_name",getIntent().getStringExtra("f_name").toString());
                intent.putExtra("l_name",getIntent().getStringExtra("l_name").toString());
                //intent.putExtra("m_name",getIntent().getStringArrayExtra("m_name").toString());
                intent.putExtra("email",getIntent().getStringExtra("email").toString());
                intent.putExtra("img_url",getIntent().getStringExtra("img_url").toString());
                startActivity(intent);
            }
        });


    }
}