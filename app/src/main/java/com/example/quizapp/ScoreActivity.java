package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView scrore,scoretext;
    Button playagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scrore=findViewById(R.id.showscore);
        playagain=findViewById(R.id.playagain);
        scoretext=findViewById(R.id.scoretext);
        scrore.setText(getIntent().getStringExtra("SCORE").toString());
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScoreActivity.this,Descrpion_Grid.class));
            }
        });
    }
}