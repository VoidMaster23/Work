package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class Death extends Fragment {
    JSONArray CategoryType;

    JSONObject death;
    EditText location, time, place, date, name, post, time2;
    CheckBox carotidPulseLeft, carotidPulseRight, breathingYes, breathingNo, eyeYes, eyeNo, ecgYes, ecgNo, pupilsYes, pupilsNo;
    String carotidPulse, breathing, dollEyeMovements, ecgStraightLine, bilateralFixedDilatedPupils;

    public Death(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_death,container,false);

        location = view.findViewById(R.id.locationEdit);
        time = view.findViewById(R.id.timeEdit);
        place = view.findViewById(R.id.placeEdit);
        date = view.findViewById(R.id.dateEdit);
        name = view.findViewById(R.id.nameEdit);
        post = view.findViewById(R.id.postEdit);
        time2 = view.findViewById(R.id.timeEdit2);

        carotidPulseLeft = view.findViewById(R.id.leftChk);
        carotidPulseRight = view.findViewById(R.id.rightChk);
        breathingYes = view.findViewById(R.id.breathingChkYes);
        breathingNo = view.findViewById(R.id.breathingChkNo);
        eyeYes = view.findViewById(R.id.dollChkYes);
        eyeNo = view.findViewById(R.id.dollChkNo);
        ecgYes = view.findViewById(R.id.ecgChkYes);
        ecgNo = view.findViewById(R.id.ecgChkNo);
        pupilsYes = view.findViewById(R.id.dilatedChkYes);
        pupilsNo = view.findViewById(R.id.dilatedChkNo);
        death = new JSONObject();
        CategoryType = new JSONArray();

        try {
            CategoryType = new JSONArray(getActivity().getIntent().getStringExtra("Category Type"));
        }catch (Exception e) {}

        return view;
    }

    public JSONObject Send (View v){
        death = new JSONObject();
        //carotid pulse
        if(carotidPulseLeft.isChecked()) carotidPulse = carotidPulseLeft.getText().toString();
        if(carotidPulseRight.isChecked()) carotidPulse = carotidPulseRight.getText().toString();

        //breathing
        if(breathingNo.isChecked()) breathing = breathingNo.getText().toString();
        if(breathingYes.isChecked()) breathing = breathingYes.getText().toString();

        //Doll eye movements
        if(eyeYes.isChecked()) dollEyeMovements = eyeYes.getText().toString();
        if(eyeNo.isChecked()) dollEyeMovements = eyeNo.getText().toString();

        //Ecg straight Line
        if(ecgYes.isChecked()) ecgStraightLine = ecgYes.getText().toString();
        if(ecgNo.isChecked()) ecgStraightLine = ecgNo.getText().toString();

        //bilateral fixed dilated pupils
        if(pupilsNo.isChecked()) bilateralFixedDilatedPupils = pupilsNo.getText().toString();
        if(pupilsYes.isChecked()) bilateralFixedDilatedPupils = pupilsYes.getText().toString();

        try{
            death.put("Carotid Pulse",carotidPulse);
            death.put("Breathing",breathing);
            death.put("Doll Eye Movements",dollEyeMovements);
            death.put("ECG Straight Line",ecgStraightLine);
            death.put("Bilateral Fixed Dilated Pupils",bilateralFixedDilatedPupils);
            death.put("Location of Deceased",location.toString());
            death.put("Time of Examination",time.toString());
            death.put("Place",place.toString());
            death.put("Date",date.toString());
            death.put("Full Name",name.toString());
            death.put("Post",post.toString());
            death.put("Time",time2.toString());

        }catch (Exception e){}



        return death;
    }

    public void send(){
        // do some stuff here but eh im tired

        try {
            Intent i = new Intent(getContext(), Home_Screen_Crew.class);
            startActivity(i);
        }catch (Exception e){}
    }


}
