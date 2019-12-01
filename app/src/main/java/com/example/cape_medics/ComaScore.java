package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class ComaScore extends Fragment {

    String[] eyeResp = {"No Eye Opening","Open To Pain","Open To Verbal Command","Open Spontaneously"};
    String[] verbResp = {"No Verbal Response","Incomprehensible Sounds","Inappropriate Words","Confused","Orientated"};
    String[] motorResp = {"No Motor Response","Extension To Pain","Flexion To Pain","Withdraws From Pain","Localises Pain","Obeys Commands"};
    String[] bloodPress = {"0","1-49","50-75","76-89",">89"};
    String[] rate = {"0","1-5","6-9",">29","10-29"};
    TextView gcs, trauma;

    Spinner eye, verb, motor, blood, respiration;

    ArrayAdapter eyeAdapter, verbAdapter, motorAdapter, bloodAdapter, respAdapter;

    String gScore, tScore;

    JSONObject comaScore;

    String eyeStr, verbStr, motorStr,bloodStr, respStr;
    CheckBox chkNA;

    public ComaScore() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coma_score, container, false);

        gcs = view.findViewById(R.id.lblGCS);
        trauma  = view.findViewById(R.id.lblTrauma);

        chkNA = view.findViewById( R.id.notApplicableCheckBox );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        eye = view.findViewById(R.id.spnEyeResponse);
        verb = view.findViewById(R.id.spnVerbalResponse);
        motor = view.findViewById(R.id.spnMotorResponse);

        eyeAdapter = new ArrayAdapter(getContext(),R.layout.custom_spinner,eyeResp);
        verbAdapter = new ArrayAdapter(getContext(),R.layout.custom_spinner,verbResp);
        motorAdapter = new ArrayAdapter(getContext(),R.layout.custom_spinner,motorResp);

        eyeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        verbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        eye.setAdapter(eyeAdapter);
        verb.setAdapter(verbAdapter);
        motor.setAdapter(motorAdapter);

        //for rts
        blood = view.findViewById(R.id.spnsbp);
        respiration = view.findViewById(R.id.spnrr);

        bloodAdapter = new ArrayAdapter(getContext(),R.layout.custom_spinner,bloodPress);
        respAdapter = new ArrayAdapter(getContext(),R.layout.custom_spinner,rate);

        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        respAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        blood.setAdapter(bloodAdapter);
        respiration.setAdapter(respAdapter);





        gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
        gcs.setText(gScore);

        tScore = "Revised Trauma Score: "+traumaScore()+"/12";
        trauma.setText(tScore);

       eye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
               gcs.setText(gScore);

               tScore = "Revised Trauma Score: "+traumaScore()+"/12";
               trauma.setText(tScore);

               eyeStr = eyeResp[i];
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        motor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                motorStr = motorResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        verb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                verbStr = verbResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);
                bloodStr = bloodPress[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        respiration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);
                respStr = rate[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return view;
    }

    public int gcsScore (){
        int eyeScore = eye.getSelectedItemPosition()+1;
        int verbScore = verb.getSelectedItemPosition()+1;
        int motorScore = motor.getSelectedItemPosition()+1;

        return eyeScore+verbScore+motorScore;

    }

    public int traumaScore(){
        int trauma = 0;

        //bsed on the coma score
        if(gcsScore() >= 13){
            trauma += 4;
        }else if(gcsScore() >= 9){
            trauma += 3;
        } else if( gcsScore() >= 6 ){
            trauma += 2;
        }else if(gcsScore() >= 4){
            trauma += 1;
        }else{
            trauma += 0;
        }

        int bloodVal = blood.getSelectedItemPosition();
        int rateVal = respiration.getSelectedItemPosition();

        trauma  = trauma + bloodVal + rateVal;


        return trauma;
    }

    public JSONObject  createJson(){
        comaScore = new JSONObject();
        try{
            if(!chkNA.isChecked()) {
                comaScore.put("Eye Response", eyeStr);
                comaScore.put("Verbal Response", verbStr);
                comaScore.put("Motor Response", motorStr);

                comaScore.put("GCS", gcs.getText().toString());
                comaScore.put("RTS", trauma.getText().toString());
            }else{
                comaScore.put("Status","Not Applicable");
            }
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return comaScore;
    }


}
