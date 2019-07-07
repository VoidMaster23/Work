package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class Mobility extends Fragment {
    CheckBox stretcher, wheelchair, walking, crutches;
    CheckBox neck, scoop, spinal, vacuum;

    public Mobility(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mobility,container,false);



        //deal with this in send
        stretcher =  view.findViewById(R.id.stretcherChk);
        wheelchair =  view.findViewById(R.id.wheelchairChk);
        walking =  view.findViewById(R.id.walkingChk);
        crutches =  view.findViewById(R.id.crutchesChk);

        //deal with this in onclick
        neck =  view.findViewById(R.id.neckChk);
        scoop =  view.findViewById(R.id.scoopChk);
        spinal =  view.findViewById(R.id.spinalChk);
        vacuum =  view.findViewById(R.id.vacuumChk);

        stretcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()){
                    wheelchair.setChecked(false);
                }
                if(walking.isChecked()){
                    walking.setChecked(false);
                }
                if(crutches.isChecked()){
                    crutches.setChecked(false);
                }
            }
        });

        wheelchair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stretcher.isChecked()){
                    stretcher.setChecked(false);
                }
                if(walking.isChecked()){
                    walking.setChecked(false);
                }
                if(crutches.isChecked()){
                    crutches.setChecked(false);
                }
            }
        });

        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()){
                    wheelchair.setChecked(false);
                }
                if(stretcher.isChecked()){
                    stretcher.setChecked(false);
                }
                if(crutches.isChecked()){
                    crutches.setChecked(false);
                }
            }
        });

        crutches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()){
                    wheelchair.setChecked(false);
                }
                if(walking.isChecked()){
                    walking.setChecked(false);
                }
                if(stretcher.isChecked()){
                    stretcher.setChecked(false);
                }
            }
        });
        return view;
    }

    public void skip(View v){

    }
}
