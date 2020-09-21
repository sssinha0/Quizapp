package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_detail extends AppCompatActivity {
    CircleImageView circleImageView;
    TextView proname,proemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        proname=findViewById(R.id.proname);
        proemail=findViewById(R.id.proemail);
        circleImageView=findViewById(R.id.proimage);
        Glide.with(profile_detail.this).load(getIntent().getStringExtra("img_url").toString()).into(circleImageView);
        String f_name=getIntent().getStringExtra("f_name").toString();
       // String m_name=getIntent().getStringExtra("m_name").toString();
        String l_name=getIntent().getStringExtra("l_name").toString();
        proemail.setText(getIntent().getStringExtra("email").toString());
        proname.setText(f_name+l_name);
    }
}