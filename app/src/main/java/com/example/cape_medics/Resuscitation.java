package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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


public class Resuscitation extends Fragment {
    private ImageView imageView19;
    public CheckBox chkNA;
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
    private EditText edtStartCPR;
    private TextView textView29;
    private EditText edtEndCPR;
    private TextView textView30;
    private EditText edtDrugIssue;
    private CheckBox chkWitness;
    private EditText rosc;
    private EditText firstPush;


    JSONObject resicitation;

    TextView textView;
    int minutes, sec;
    String time;

    Button start,stop,reset;

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
        chkNA = (CheckBox)view.findViewById( R.id.notApplicableCheckBox );
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
        edtStartCPR = (EditText)view.findViewById( R.id.edtStartCPR );
        textView29 = (TextView)view.findViewById( R.id.textView29 );
        edtEndCPR = (EditText)view.findViewById( R.id.edtEndCPR );
        textView30 = (TextView)view.findViewById( R.id.textView30 );
        edtDrugIssue = (EditText)view.findViewById( R.id.edtDrugIssue );
        chkWitness = (CheckBox)view.findViewById( R.id.chkWitness );
        rosc = view.findViewById(R.id.rosceditTextView);
        firstPush = view.findViewById(R.id.edtDrugIssue);

        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });


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


    public JSONObject createJson(){

        resicitation = new JSONObject();
        String provided = null;

        if(chkBystander.isChecked()){
            provided = chkBystander.getText().toString();
        }else if(chkEms.isChecked()){
            provided = chkEms.getText().toString();
        }else if(chkFirst.isChecked()){
            provided = chkFirst.getText().toString();
        }else if(chkOther.isChecked()){
            provided = chkOther.getText().toString();
        }

        String items = null;

        if (chkPads.isChecked()){
            items = chkPads.getText().toString();
        }else if(chkPaddles.isChecked()){
            items = chkPaddles.getText().toString();
        }


        String cpr = null;
        if(chkCPRo.isChecked()){
            cpr = chkCPRo.getText().toString();
        }else if(chkIncAirway.isChecked()){
            cpr = chkIncAirway.getText().toString();

        }


        String start = edtStartCPR.getText().toString();
        String end  = edtEndCPR.getText().toString();
        String roscT = rosc.getText().toString();
        String timeP = firstPush.getText().toString();


        try{
            resicitation.put("Provided",provided);
            resicitation.put("Items",items);
            resicitation.put("CPR Details",cpr);
            resicitation.put("Start",start);
            resicitation.put("End",end);
            resicitation.put("ROSC", roscT);
            resicitation.put("Time 1st drug pushed", timeP);
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    return resicitation;
    }

    public boolean validate(){
        boolean valid = true;
        String provided = null;

        if(chkBystander.isChecked()){
            provided = chkBystander.getText().toString();
        }else if(chkEms.isChecked()){
            provided = chkEms.getText().toString();
        }else if(chkFirst.isChecked()){
            provided = chkFirst.getText().toString();
        }else if(chkOther.isChecked()){
            provided = chkOther.getText().toString();
        }

        String items = null;

        if (chkPads.isChecked()){
            items = chkPads.getText().toString();
        }else if(chkPaddles.isChecked()){
            items = chkPaddles.getText().toString();
        }


        String cpr = null;
        if(chkCPRo.isChecked()){
            cpr = chkCPRo.getText().toString();
        }else if(chkIncAirway.isChecked()){
            cpr = chkIncAirway.getText().toString();

        }


        String start = edtStartCPR.getText().toString();
        String end  = edtEndCPR.getText().toString();
        String roscT = rosc.getText().toString();
        String timeP = firstPush.getText().toString();


        if(provided.isEmpty() || items.isEmpty() || cpr.isEmpty() || start.isEmpty() || end.isEmpty() || roscT.isEmpty() || timeP.isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Please make sure resuscitation has been filled in", Toast.LENGTH_SHORT).show();
        }

        String reg = MedicalFormCalls.toMatch;
        if(!start.matches(reg) || !end.matches(reg) || !roscT.matches(reg) || timeP.matches(reg)){
            valid = false;
            Toast.makeText(getContext(),"Please make sure times are in hh:mm format", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

}
