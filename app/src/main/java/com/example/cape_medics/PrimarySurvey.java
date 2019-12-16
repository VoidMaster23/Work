package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class PrimarySurvey extends Fragment {
    private CheckBox chkNA;
    private TextView lblTime;
    private TextView edtTime;
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
    List<CheckBox> checkBoxList, airwayList, respirationList, pulseList, descriptiveList, capRefill;
    String time, airway, respiration, pulse, rate,Refil;
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
        chkNA = (CheckBox)view.findViewById( R.id.notApplicableCheckBox );
        chkNA.setOnClickListener(this::makeNa);
        lblTime = (TextView)view.findViewById( R.id.lblTime );
        edtTime = view.findViewById( R.id.edtTime );
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
        airwayList = Arrays.asList(chkClear,chkOcc,chkNoisy,chkSpine);


        //prevent multiple checkboxes from being checked
        for(CheckBox c: airwayList){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: airwayList){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }

        respirationList = Arrays.asList(chkHigh,chkLow,chkMed,chSix,chkZero);
        for(CheckBox c: respirationList){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: respirationList){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }

        pulseList = Arrays.asList(chkRadial,chkBrachial,chkCarotid,chkFemoral,chkAbsent);
        for(CheckBox c: pulseList){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: pulseList){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }
        descriptiveList = Arrays.asList(chkAbnormal,chkAbsent);
        for(CheckBox c: descriptiveList){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: descriptiveList){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }
        capRefill = Arrays.asList(chkTwoMinus,chkTwoPlus);
        for(CheckBox c: capRefill){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: capRefill){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }
        // end of checkbox stuff

        TimePicker();

        TimePicker();

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

    Context mContext;
    private void TimePicker() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mContext = getActivity();

        edtTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtTime.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });
    }


    public JSONObject createJson(){

        //Time
         time = edtTime.getText().toString();

        //
         airway = null;

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
        respiration = null;
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
         pulse = null;

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
         rate = null;
        if(chkNormal.isChecked()){
            rate = chkNormal.getText().toString();
        }else if(chkAbnormal.isChecked()){
            airway = chkAbnormal.getText().toString();
        }

        //Refil
         Refil = null;

        if(chkTwoPlus.isChecked()){
            Refil = chkTwoPlus.getText().toString();
        }else if(chkTwoMinus.isChecked()){
            Refil = chkTwoMinus.getText().toString();
        }

        //Create JSOn
        primarySurvery = new JSONObject();
        try{
            if(!chkNA.isChecked() && validate()) {
                primarySurvery.put("Time", time);
                primarySurvery.put("Airway", airway);
                primarySurvery.put("Respiration", respiration);
                primarySurvery.put("Pulse", pulse);
                primarySurvery.put("Rate", rate);
                primarySurvery.put("Refill", Refil);
            }else if(chkNA.isChecked()){
                primarySurvery.put("Status","Not applicable");
            }
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("primarySurvery",primarySurvery.toString());

        return primarySurvery;
    }

    public void makeNa(View v){


        medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);

    }

    public boolean validate(){
        boolean valid = true;
        Looper.prepare();

        if(time.isEmpty()){
            valid = false;
            Toast.makeText(getContext(), "Primary Survey: Please enter the time of survey",Toast.LENGTH_SHORT).show();

        }

        if(valid){
            if(airway.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Primary Survey: Please ensure all information has been entered",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(respiration.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Primary Survey: Please ensure all information has been entered",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(pulse.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Primary Survey: Please ensure all information has been entered",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(rate.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Primary Survey: Please ensure all information has been entered",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(Refil.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Primary Survey: Please ensure all information has been entered",Toast.LENGTH_SHORT).show();
            }
        }






        Looper.loop();
        return valid;
    }



}