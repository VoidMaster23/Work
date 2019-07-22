package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class TimeSheet extends AppCompatActivity {
    Spinner jobType;
    Spinner unitType;
    Spinner serviceProvided;
    EditText callTime;
    EditText jobName;
    EditText Location;
    JSONObject timeSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);

        timeSheet = new JSONObject();
        jobName = findViewById(R.id.editText);
        Location = findViewById(R.id.editText2);
        callTime = findViewById(R.id.editText3);
        jobType = findViewById(R.id.spinner4);
        unitType = findViewById(R.id.spinner5);
        serviceProvided = findViewById(R.id.spinner6);


        String[] JobType = {"FILM","EVENT","COMMERCIAL","REHEARSAL","RIGGING","TRAINING","OFFICE STAFF"};
        String[] UnitType = {"MAIN UNIT","2ND UNIT","SPLINTER UNIT","REHEARSAL","RIGGING","CONSTRUCTION",
                "OFFICE STAFF","BUILD UP","STRIKE","RECCE","SAFETY INSPECTION","ACCIDENT INVESTIGATION",
                "AIR MONITORING","EAP","RISK ASSESSMENT"};
        String[] ServiceProvided = {"SKID UNIT","MEDIC","AMBULANCE","FIRE MARSHAL","FIRE ENGINE","SAFETY ADVISOR","" +
                "SAFETY MARSHAL","RESCUE","RESPONSE CAR","WATER TANKER","4X4 SKID","OFFICE STAFF"};
        //  String[] CallTime = {""};

        ArrayAdapter<String> JobTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,JobType);
        ArrayAdapter<String> UnitTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,UnitType);
        ArrayAdapter<String> ServiceProvidedAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,ServiceProvided);
        // ArrayAdapter<String> CallTimeAdapter = new ArrayAdapter<String>(this,
        //       android.R.layout.simple_spinner_item,CallTime);

        JobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ServiceProvidedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // CallTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jobType.setAdapter(JobTypeAdapter);
        unitType.setAdapter(UnitTypeAdapter);
        serviceProvided.setAdapter(ServiceProvidedAdapter);
        //callTime.setAdapter(CallTimeAdapter);

    }

    public void StartShift(View v){
        try{
            timeSheet.put("Job Name",jobName.getText().toString());
            timeSheet.put("Location",Location.getText().toString());
            timeSheet.put("Call Time",callTime.getText().toString());
            timeSheet.put("Job Type",String.valueOf(jobType));
            timeSheet.put("Unit Type",String.valueOf(unitType));
            timeSheet.put("Service Provided",String.valueOf(serviceProvided));

        }catch(Exception e){}

        //send all the above info

        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    public void Cancel(View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    /*public String[] TimeCreator(){
        String Final = "";
        String min = "";
        String h = "";
        String d = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        String Date = DateTime.substring(0,10);
        String Time = DateTime.substring(11,16);

        String[] TimeArray = Time.split(":");
        String[] DateArray = Date.split("/");
        int minutes = Integer.parseInt(TimeArray[1]);
        int hours = Integer.parseInt(TimeArray[0]);
        int days = Integer.parseInt(DateArray[2]);

        //initial rounding
        if (minutes<15){
            minutes = 15;
            min = "15";
        }
        else if (minutes<30){
            minutes = 30;
            min = "30";
        }
        else if (minutes<45){
            minutes = 45;
            min = "45";
        }
        else{
            minutes = 0;
            min = "00";
        }

        Time = TimeArray[0]+":"+min;
        Final = Final + "CallTime: "+Time+"-"+Date;

        for(int i=0;i<96;i++){
            minutes+=15;
            if(minutes == 60){
                minutes = 0;
                hours+=1;
                if (hours>9) {
                    h = Integer.toString(hours);
                }
                else{
                    h = Integer.toString(hours);
                    h = "0"+h;
                }
                min = "00";
            }
            if(hours == 24){
                minutes = 0;
                hours = 0;
                min = "00";
                h = "00";
                days+= 1;
                if (days>9) {
                    d = Integer.toString(days);
                }
                else{
                    d = Integer.toString(days);
                    d = "0"+h;
                }
            }

        }



        return null;
    }*/
}
