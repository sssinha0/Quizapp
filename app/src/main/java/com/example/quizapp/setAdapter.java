package com.example.quizapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class setAdapter extends BaseAdapter {
private  int numofset;

    public setAdapter(int numofset) {
        this.numofset = numofset;
    }

    @Override
    public int getCount() {
        return numofset;
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
    public View getView(int i, View view, final ViewGroup viewGroup) {
        View view1;
        if(view==null){
            view1= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_layout,viewGroup,false);

        }else
            view1=view;
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(viewGroup.getContext(),QuestionActivity.class);
                viewGroup.getContext().startActivity(intent);
            }
        });
        ((TextView) view1.findViewById(R.id.setnum)).setText(String.valueOf(i+1));
        return view1;
    }
}
