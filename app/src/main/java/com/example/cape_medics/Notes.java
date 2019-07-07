package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Notes extends Fragment {

    public Notes(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_notes, container, false);

        return view;
    }

    public void skip (View v){
        //send it as this is last one
        Intent i = new Intent(getContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    public void Send (View v){
        Intent i = new Intent(getContext(), Home_Screen_Crew.class);
        startActivity(i);
    }
}
