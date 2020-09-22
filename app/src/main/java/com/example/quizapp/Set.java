package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;



public class Set extends AppCompatActivity {
    Toolbar toolbar;
    GridView setgridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        toolbar=findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SET");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setgridview=findViewById(R.id.setgrid);
        setAdapter adapter=new setAdapter(6);
        setgridview.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Set.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}