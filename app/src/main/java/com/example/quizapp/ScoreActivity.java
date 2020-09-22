package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView scrore,scoretext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scrore=findViewById(R.id.showscore);
        scoretext=findViewById(R.id.scoretext);
        scrore.setText(getIntent().getStringExtra("SCORE").toString());
    }
}