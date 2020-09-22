package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class GridAdapter extends BaseAdapter {
    List<String> catgrid;

    public GridAdapter(List<String> catgrid) {
        this.catgrid = catgrid;
    }

    @Override
    public int getCount() {
        return catgrid.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
       View view1;
        if(view==null){
            view1= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_grid,viewGroup,false);

        }else{
view1=view;
        }
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(viewGroup.getContext(),Set.class);
                intent.putExtra("Catogery",catgrid.get(i));
                viewGroup.getContext().startActivity(intent);
            }
        });
        ( (TextView) view1.findViewById(R.id.catogery)).setText(catgrid.get(i));
        Random rand=new Random();
        int color= Color.argb(255,rand.nextInt(255),rand.nextInt(255) ,rand.nextInt(255));
        view1.setBackgroundColor(color);
        return view1;
    }
}
