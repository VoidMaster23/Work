package com.example.cape_medics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class Death extends Fragment {
    JSONArray CategoryType;
    Button send, go;
    JSONObject death;
    EditText location, time, place, date, name, post, time2;
    CheckBox carotidPulseLeft, carotidPulseRight, breathingYes, breathingNo, eyeYes, eyeNo, ecgYes, ecgNo, pupilsYes, pupilsNo;
    String carotidPulse, breathing, dollEyeMovements, ecgStraightLine, bilateralFixedDilatedPupils;
    private static final String IMAGE_DIRECTORY = "/Pictures";

    public Death(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_death,container,false);



        death = new JSONObject();

        send = view.findViewById(R.id.send);
        go = view.findViewById(R.id.signatureButton);
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
        CategoryType = new JSONArray();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Death ");
                startActivity(i);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    medicalTabbedView.map.put("Call Details", medicalTabbedView.medicalFormCalls.createJson());
                    medicalTabbedView.map.put("Patient Details", medicalTabbedView.patientDetails.createJson());
                    medicalTabbedView.map.put("Primary Survey",medicalTabbedView.primarySurvey.createJson());
                    medicalTabbedView.map.put("Presenting Condition", medicalTabbedView.presentingCondition.createJson());
                    medicalTabbedView.map.put("Sample History",medicalTabbedView.sampleHistory.createJson());
                    medicalTabbedView.map.put("Vital Signs",medicalTabbedView.vitalSigns.createJson());
                    medicalTabbedView.map.put("Airway & Breathing Management", medicalTabbedView.airwayBreathing.createJson());
                    medicalTabbedView.map.put("Airway Adjunct", medicalTabbedView.airwayAdjunct.createJson());
                    medicalTabbedView.map.put("Resuscitation", medicalTabbedView.resuscitation.createJson());
                    medicalTabbedView.map.put("Cannulation", medicalTabbedView.cannulation.createJson());
                    medicalTabbedView.map.put("Drugs Administered",medicalTabbedView.drugsAdministered.createJson());
                    medicalTabbedView.map.put("Injury Matrix",medicalTabbedView.injuryMatrix.createJson());
                    medicalTabbedView.map.put("Cardiac Monitoring", medicalTabbedView.cardiacMonitoring.createJson());
                    medicalTabbedView.map.put("Glasgow Coma Score", medicalTabbedView.comaScore.createJson());
                    medicalTabbedView.map.put("Wound Care", medicalTabbedView.woundCare.createJson());
                    medicalTabbedView.map.put("Burns", medicalTabbedView.burns.createJson());
                    medicalTabbedView.map.put("Pain Score", medicalTabbedView.score.createJson());
                    medicalTabbedView.map.put("Stroke",medicalTabbedView.stroke.createJson());
                    medicalTabbedView.map.put("APGAR Score", medicalTabbedView.apgarScore.createJson());
                    medicalTabbedView.map.put("BLS/ILS Aid",medicalTabbedView.blsAid.createJson());
                    medicalTabbedView.map.put("Treatment",medicalTabbedView.treatment.createJson());
                    medicalTabbedView.map.put("Ventilator Settings",medicalTabbedView.ventilatorSettings.createJson());
                    medicalTabbedView.map.put("Employers Details",medicalTabbedView.employerDetails.createJson());
                    medicalTabbedView.map.put("Payment Method",medicalTabbedView.paymentMethod.createJson());
                    medicalTabbedView.map.put("Guarantee of payment",medicalTabbedView.payment.createJson());
                    medicalTabbedView.map.put("Refusal of care",medicalTabbedView.refusalofCare.createJson());
                    medicalTabbedView.map.put("Mobility",medicalTabbedView.mobility.createJson());
                    medicalTabbedView.map.put("Disposal",medicalTabbedView.disposal.createJson());
                    medicalTabbedView.map.put("Crew Details", medicalTabbedView.crewDetails.createJson());
                    medicalTabbedView.map.put("Accompanying Practitioner", medicalTabbedView.acompPrac.createJson());
                    medicalTabbedView.map.put("Items Handed over", medicalTabbedView.handed.createJson());
                    medicalTabbedView.map.put("Handover/disposal",medicalTabbedView.handoff_frag.createJson());
                    medicalTabbedView.map.put("Notes",medicalTabbedView.notes.createJson());
                    medicalTabbedView.map.put("Death",createJson());
                    Log.i("MEDICAL DATA",medicalTabbedView.map.toString());

                    Intent i = new Intent(getContext(), Home_Screen_Crew.class);
                    i.putExtra("first","false");
                    i.putExtra("code",Home_Screen_Crew.code);

                    startActivity(i);
                    
                    
                }catch (Exception e){}
            }
        });

        try {
            CategoryType = new JSONArray(getActivity().getIntent().getStringExtra("Category Type"));
        }catch (Exception e) {}

        return view;
    }

    public void removeStringPropertys(String code, Cache cache, Context context){
        //use this method to remove all string caches
        cache = new Cache(context);
        cache.removeStringProperty("categoryType"+code);
    }

    public JSONObject createJson (){

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
            death.put("Commissioner of Oaths",loadImage());
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

    public Bitmap loadImage(){
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY,"Death.jpg");

        if(wallpaperDirectory.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(wallpaperDirectory.getAbsolutePath());
            return myBitmap;
        }
        else{
            return null;
        }



    }






}
