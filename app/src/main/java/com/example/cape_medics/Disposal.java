package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.json.JSONObject;

public class Disposal extends Fragment {
    JSONObject disposal;
    String hospital_home_familyGP, ambulance_car_ownTransport, advice_refusedHelp_refusedSign_returnedToWork;
    CheckBox home, hospital, family;
    CheckBox ambulance, car, own;
    CheckBox advice, help, sign, Return;

    public Disposal(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_disposal,container,false);
        disposal = new JSONObject();

        home = view.findViewById(R.id.homeChk);
        family = view.findViewById(R.id.familyChk);
        hospital = view.findViewById(R.id.hospitalChk);

        ambulance = view.findViewById(R.id.ambulanceChk);
        car = view.findViewById(R.id.carChk);
        own = view.findViewById(R.id.ownChk);

        advice = view.findViewById(R.id.adviceChk);
        help = view.findViewById(R.id.helpChk);
        sign = view.findViewById(R.id.signChk);
        Return = view.findViewById(R.id.returnChk);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (family.isChecked()){
                    family.setChecked(false);
                }
                if(hospital.isChecked()){
                    hospital.setChecked(false);
                }
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (home.isChecked()){
                    home.setChecked(false);
                }
                if(hospital.isChecked()){
                    hospital.setChecked(false);
                }
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (family.isChecked()){
                    family.setChecked(false);
                }
                if(home.isChecked()){
                    home.setChecked(false);
                }
            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (car.isChecked()){
                    car.setChecked(false);
                }
                if(own.isChecked()){
                    own.setChecked(false);
                }
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ambulance.isChecked()){
                    ambulance.setChecked(false);
                }
                if(own.isChecked()){
                    own.setChecked(false);
                }
            }
        });

        own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (car.isChecked()){
                    car.setChecked(false);
                }
                if(ambulance.isChecked()){
                    ambulance.setChecked(false);
                }
            }
        });

        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (help.isChecked()){
                    help.setChecked(false);
                }
                if(sign.isChecked()){
                    sign.setChecked(false);
                }
                if(Return.isChecked()){
                    Return.setChecked(false);
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (advice.isChecked()){
                    advice.setChecked(false);
                }
                if(sign.isChecked()){
                    sign.setChecked(false);
                }
                if(Return.isChecked()){
                    Return.setChecked(false);
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (help.isChecked()){
                    help.setChecked(false);
                }
                if(advice.isChecked()){
                    advice.setChecked(false);
                }
                if(Return.isChecked()){
                    Return.setChecked(false);
                }
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (help.isChecked()){
                    help.setChecked(false);
                }
                if(sign.isChecked()){
                    sign.setChecked(false);
                }
                if(advice.isChecked()){
                    advice.setChecked(false);
                }
            }
        });

        return view;
    }


    public JSONObject createJson(){
        disposal = new JSONObject();
        if(home.isChecked()) hospital_home_familyGP = home.getText().toString();
        if(hospital.isChecked()) hospital_home_familyGP = hospital.getText().toString();
        if(family.isChecked()) hospital_home_familyGP = family.getText().toString();

        if(ambulance.isChecked()) ambulance_car_ownTransport = ambulance.getText().toString();
        if(car.isChecked()) ambulance_car_ownTransport = car.getText().toString();
        if(own.isChecked()) ambulance_car_ownTransport = own.getText().toString();

        if(advice.isChecked()) advice_refusedHelp_refusedSign_returnedToWork = advice.getText().toString();
        if(help.isChecked()) advice_refusedHelp_refusedSign_returnedToWork = help.getText().toString();
        if(Return.isChecked()) advice_refusedHelp_refusedSign_returnedToWork = Return.getText().toString();
        if(sign.isChecked()) advice_refusedHelp_refusedSign_returnedToWork = sign.getText().toString();

        try{
            disposal.put("Hospital/Home/Family GP", hospital_home_familyGP);
            disposal.put("Ambulance/Car/Own Transport", ambulance_car_ownTransport);
            disposal.put("Advice Only/Refused Help/Refused to Sign/Return to Work/Venue", advice_refusedHelp_refusedSign_returnedToWork);
        }catch(Exception e){}
        return disposal;
    }

    public void skip(View v){

    }
}
