package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class PrimarySurvey extends Fragment {
    private CheckBox chkNA;
    private TextView lblTime;
    private EditText edtTime;
    private TextView textView14;
    private CheckBox chkClear;
    private CheckBox chkOcc;
    private CheckBox chkNoisy;
    private CheckBox chkSpine;
    private TextView textView16;
    private CheckBox chkRadial;
    private CheckBox chkBrachial;
    private CheckBox chkCarotid;
    private CheckBox chkFemoral;
    private CheckBox chkAbsent;
    private TextView textView15;
    private CheckBox chkHigh;
    private CheckBox chkMed;
    private CheckBox chSix;
    private CheckBox chkLow;
    private CheckBox chkZero;
    private TextView textView17;
    private CheckBox chkNormal;
    private CheckBox chkAbnormal;
    private TextView textView18;
    private CheckBox chkTwoPlus;
    private CheckBox chkTwoMinus;
    List<CheckBox> checkBoxList;
    JSONObject load;
    Cache cache;
    String saved;


JSONObject primarySurvery;




    public PrimarySurvey() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_primary_survey, container, false);
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        lblTime = (TextView)view.findViewById( R.id.lblTime );
        edtTime = (EditText)view.findViewById( R.id.edtTime );
        textView14 = (TextView)view.findViewById( R.id.textView14 );
        chkClear = (CheckBox)view.findViewById( R.id.chkClear );
        chkOcc = (CheckBox)view.findViewById( R.id.chkOcc );
        chkNoisy = (CheckBox)view.findViewById( R.id.chkNoisy );
        chkSpine = (CheckBox)view.findViewById( R.id.chkSpine );
        textView16 = (TextView)view.findViewById( R.id.textView16 );
        chkRadial = (CheckBox)view.findViewById( R.id.chkRadial );
        chkBrachial = (CheckBox)view.findViewById( R.id.chkBrachial );
        chkCarotid = (CheckBox)view.findViewById( R.id.chkCarotid );
        chkFemoral = (CheckBox)view.findViewById( R.id.chkFemoral );
        chkAbsent = (CheckBox)view.findViewById( R.id.chkAbsent );
        textView15 = (TextView)view.findViewById( R.id.textView15 );
        chkHigh = (CheckBox)view.findViewById( R.id.chkHigh );
        chkMed = (CheckBox)view.findViewById( R.id.chkMed );
        chSix = (CheckBox)view.findViewById( R.id.chSix );
        chkLow = (CheckBox)view.findViewById( R.id.chkLow );
        chkZero = (CheckBox)view.findViewById( R.id.chkZero );
        textView17 = (TextView)view.findViewById( R.id.textView17 );
        chkNormal = (CheckBox)view.findViewById( R.id.chkNormal );
        chkAbnormal = (CheckBox)view.findViewById( R.id.chkAbnormal );
        textView18 = (TextView)view.findViewById( R.id.textView18 );
        chkTwoPlus = (CheckBox)view.findViewById( R.id.chkTwoPlus );
        chkTwoMinus = (CheckBox)view.findViewById( R.id.chkTwoMinus );
        checkBoxList = Arrays.asList(chkAbnormal,chkAbsent,chkClear,chkOcc,chkNoisy,chkSpine,chkRadial,chkBrachial,chkCarotid,chkFemoral,chkHigh,chkLow,chkMed,chSix,chkZero,chkNormal,chkTwoMinus,chkTwoPlus);


        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });
        cache = new Cache(getContext());
        saved = cache.getStringProperty("primarySurvery");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                Iterator<String> keys = load.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j<checkBoxList.size();j++) {
                        if (checkBoxList.get(j) != null) {
                            if (item.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);

                            }
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    public JSONObject createJson(){

        //Time
        String time = edtTime.getText().toString();

        //
        String airway = null;

        if(chkClear.isChecked()){
            airway = chkClear.getText().toString();
        }else if(chkOcc.isChecked()){
            airway = chkOcc.getText().toString();
        }else if(chkNoisy.isChecked()){
            airway = chkNoisy.getText().toString();
        }else if(chkSpine.isChecked()){
            airway = chkSpine.getText().toString();
        }

        //Respiration Rate
        String respiration = null;
        if(chkHigh.isChecked()){
            respiration = chkHigh.getText().toString();
        }else if(chkMed.isChecked()){
            respiration = chkMed.getText().toString();
        }else if(chSix.isChecked()){
            respiration = chSix.getText().toString();
        }else if(chkLow.isChecked()){
            respiration = chkLow.getText().toString();
        }else if(chkZero.isChecked()){
            respiration = chkZero.getText().toString();
        }

        //Pulse
        String pulse = null;

        if(chkRadial.isChecked()){
            pulse = chkRadial.getText().toString();
        }else if(chkBrachial.isChecked()){
            pulse = chkBrachial.getText().toString();
        }else if(chkCarotid.isChecked()){
            pulse = chkCarotid.getText().toString();
        }else if(chkFemoral.isChecked()){
            pulse = chkFemoral.getText().toString();
        }else if(chkAbsent.isChecked()){
            pulse = chkAbsent.getText().toString();
        }

        //Rate
        String rate = null;
        if(chkNormal.isChecked()){
            rate = chkNormal.getText().toString();
        }else if(chkAbnormal.isChecked()){
            airway = chkAbnormal.getText().toString();
        }

        //Refil
        String Refil = null;

        if(chkTwoPlus.isChecked()){
            Refil = chkTwoPlus.getText().toString();
        }else if(chkTwoMinus.isChecked()){
            Refil = chkTwoMinus.getText().toString();
        }

        //Create JSOn
        primarySurvery = new JSONObject();
        try{
            primarySurvery.put("Time", time);
            primarySurvery.put("Airway", airway);
            primarySurvery.put("Respiration", respiration);
            primarySurvery.put("Pulse", pulse);
            primarySurvery.put("Rate", rate);
            primarySurvery.put("Refill", Refil);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("primarySurvery",primarySurvery.toString());

        return primarySurvery;
    }

    public boolean validate(){
        boolean valid = true;

        String time = edtTime.getText().toString();

        if(time.isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Please enter a primary survey time",Toast.LENGTH_SHORT).show();
        }

        if(valid){
            if(!time.matches(MedicalFormCalls.toMatch)){
                valid = false;
                Toast.makeText(getContext(),"Please enter a primary survey time in hh:mm format",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            boolean allEmpty = true;
            boolean firstEmpty = true, secondEmpty = true, thirdEmpty = true, fifthEmpty = true, fourthEmpty = true;
            for(int i  = 0; i < checkBoxList.size(); i++){



                if(checkBoxList.get(i).isChecked() && i < 4){
                    firstEmpty = false;
                    allEmpty = false;
                }

                if(checkBoxList.get(i).isChecked() && i >= 4 && i < 9 ){
                    secondEmpty = false;
                    allEmpty = false;
                }

                if(checkBoxList.get(i).isChecked() && i >= 9 && i < 14 ){
                    thirdEmpty = false;
                    allEmpty = false;
                }

                if(checkBoxList.get(i).isChecked() && i >= 14 && i < 16 ){
                    fourthEmpty = false;
                    allEmpty = false;
                }
                if(checkBoxList.get(i).isChecked() && i >= 16){
                    fifthEmpty = false;
                    allEmpty = false;
                }

            }
            if(allEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please fill in primary survey", Toast.LENGTH_SHORT).show();
            }else if(firstEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please check the patient airway", Toast.LENGTH_SHORT).show();
            }else if(secondEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please check the patient respiration rate", Toast.LENGTH_SHORT).show();
            }else if(thirdEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please check the patient pulse", Toast.LENGTH_SHORT).show();
            }else if(fourthEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please check the patient  respiration rate", Toast.LENGTH_SHORT).show();
            }else if(fifthEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please check the patient cap refill", Toast.LENGTH_SHORT).show();
            }

        }

        return valid;
    }


}
