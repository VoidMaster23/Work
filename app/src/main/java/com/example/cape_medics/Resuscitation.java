package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class Resuscitation extends Fragment {
    private ImageView imageView19;
    private CheckBox chkNA;
    private TextView countDown;
    private Button btnStart;
    private Button btnStop;
    private Button btnReset;
    private TextView textView25;
    private CheckBox chkBystander;
    private CheckBox chkEms;
    private CheckBox chkFirst;
    private CheckBox chkOther;
    private TextView textView26;
    private CheckBox chkPads;
    private CheckBox chkPaddles;
    private TextView textView27;
    private CheckBox chkCPRo;
    private CheckBox chkIncAirway;
    private TextView textView28;
    private TextView edtStartCPR;
    private TextView textView29;
    private TextView edtEndCPR,rosceditTextView;
    private TextView textView30;
    private TextView edtDrugIssue;
    private CheckBox chkWitness;

    List<CheckBox> provided, items, cpr;
    JSONObject resicitation;

    TextView textView;
    int minutes, sec;
    String time;

    Button start,stop,reset;
    String providedBy, itemsUsed, cprDetails, cprStart, end, ROSC, drugTime;
    public Resuscitation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resuscitation, container, false);

        minutes = 3;
        sec = 60;
        textView = view.findViewById(R.id.countDown);
        imageView19 = (ImageView)view.findViewById(R.id.imageView12);
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        btnStart = (Button)view.findViewById( R.id.btnStart );
        btnStop = (Button)view.findViewById( R.id.btnStop );
        btnReset = (Button)view.findViewById( R.id.btnReset );
        textView25 = (TextView)view.findViewById( R.id.textView25 );
        chkBystander = (CheckBox)view.findViewById( R.id.chkBystander );
        chkEms = (CheckBox)view.findViewById( R.id.chkEms );
        chkFirst = (CheckBox)view.findViewById( R.id.chkFirst );
        chkOther = (CheckBox)view.findViewById( R.id.chkOther );
        textView26 = (TextView)view.findViewById( R.id.textView26 );
        chkPads = (CheckBox)view.findViewById( R.id.chkPads );
        chkPaddles = (CheckBox)view.findViewById( R.id.chkPaddles );
        textView27 = (TextView)view.findViewById( R.id.textView27 );
        chkCPRo = (CheckBox)view.findViewById( R.id.chkCPRo );
        chkIncAirway = (CheckBox)view.findViewById( R.id.chkIncAirway );
        textView28 = (TextView)view.findViewById( R.id.textView28 );
        edtStartCPR = view.findViewById( R.id.edtStartCPR );
        textView29 = view.findViewById( R.id.textView29 );
        edtEndCPR = view.findViewById( R.id.edtEndCPR );
        rosceditTextView = view.findViewById( R.id.rosceditTextView );
        textView30 = view.findViewById( R.id.textView30 );
        edtDrugIssue = view.findViewById( R.id.edtDrugIssue );
        chkWitness = (CheckBox)view.findViewById( R.id.chkWitness );

        chkNA = view.findViewById(R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(this::makeNa);

        provided = Arrays.asList(chkBystander,chkEms,chkFirst,chkOther);
        for(CheckBox c: provided){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: provided){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }
        items = Arrays.asList(chkPaddles, chkPads);
        for(CheckBox c: items){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: items){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }
        cpr = Arrays.asList(chkCPRo, chkIncAirway);
        for(CheckBox c: items){
            c.setOnClickListener(view1 -> {

                for(CheckBox d: items){
                    if(!d.equals(c)){
                        d.setChecked(false);
                    }
                }

            });


        }

        TimePicker();

        final CountDownTimer timer = new CountDownTimer(180000,1000){
            @Override
            public void onTick(long l) {
                if(textView.getText().toString().endsWith("00") && Integer.parseInt(textView.getText().toString().substring(1,2))> 0){
                    minutes--;
                    sec = 59;
                    if (sec < 10){
                        time = "0"+ minutes +":0"+ sec;

                    }else {
                        time = "0" + minutes + ":" + sec;
                    }
                    textView.setText(time);
                }else{
                    sec--;
                    if (sec < 10){
                        time = "0"+ minutes +":0"+ sec;

                    }else {
                        time = "0" + minutes + ":" + sec;
                    }
                    textView.setText(time);

                }
            }

            @Override
            public void onFinish() {
                textView.setText("03:00");
                minutes = 3;
                sec = 60;
            }
        };

        start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });

        stop = view.findViewById(R.id.btnStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

        reset = view.findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                timer.onFinish();
            }
        });

        return view;
    }

    Context mContext;
    private void TimePicker() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mContext = getActivity();

        edtStartCPR.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtStartCPR.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        edtEndCPR.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtEndCPR.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        rosceditTextView.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> rosceditTextView.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        edtDrugIssue.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtDrugIssue.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });
    }


    public JSONObject createJson(){

        resicitation = new JSONObject();
        providedBy  = null;

        if(chkBystander.isChecked()){
            providedBy = chkBystander.getText().toString();
        }else if(chkEms.isChecked()){
            providedBy = chkEms.getText().toString();
        }else if(chkFirst.isChecked()){
            providedBy = chkFirst.getText().toString();
        }else if(chkOther.isChecked()){
            providedBy = chkOther.getText().toString();
        }

         itemsUsed = null;

        if (chkPads.isChecked()){
            itemsUsed = chkPads.getText().toString();
        }else if(chkPaddles.isChecked()){
            itemsUsed = chkPaddles.getText().toString();
        }


         cprDetails = null;
        if(chkCPRo.isChecked()){
            cprDetails = chkCPRo.getText().toString();
        }else if(chkIncAirway.isChecked()){
            cprDetails = chkIncAirway.getText().toString();

        }


        cprStart = edtStartCPR.getText().toString();
        end  = edtEndCPR.getText().toString();
        ROSC = rosceditTextView.getText().toString();
        drugTime = edtDrugIssue.getText().toString();


        try{
            if(!chkNA.isChecked() && validate()) {
                resicitation.put("Provided", providedBy);
                resicitation.put("Items", itemsUsed);
                resicitation.put("CPR Details", cprDetails);
                resicitation.put("CPR Start Time", cprStart);
                resicitation.put("CPR Discontinued", end);
                resicitation.put("ROSC", ROSC);
                resicitation.put("Time 1st drug pushed", drugTime);
            }else if(chkNA.isChecked()){
                resicitation.put("Status","Not applicable");
            }


        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return resicitation;
    }


    public void makeNa(View v){


        medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);

    }

    public boolean validate(){
        boolean valid = true;
        Looper.prepare();
        if(providedBy.isEmpty()){
            valid = false;
            Toast.makeText(getContext(), "Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();

        }

        if(valid){
            if(itemsUsed.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(cprDetails.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(cprStart.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(end.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(ROSC.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(drugTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Resuscitation: Please enter all the details",Toast.LENGTH_SHORT).show();
            }
        }

        Looper.loop();
        return valid;
    }
}