package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz_descrption extends AppCompatActivity {
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_descrption);
    play=findViewById(R.id.playquiz);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Quiz_descrption.this,Quiz_play.class);
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