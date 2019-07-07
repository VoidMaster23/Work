package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class Disposal extends Fragment {
    CheckBox home, hospital, family;
    CheckBox ambulance, car, own;
    CheckBox advice, help, sign, Return;

    public Disposal(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_disposal,container,false);


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


    public void skip(View v){

    }
}
