package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONObject;


public class ApgarScore extends Fragment {
    EditText activity, pulse, grim, look, breathe;
    Button calc;
    TextView textView;
    public CheckBox chkNA;

    JSONObject apgarScore;

    public ApgarScore() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apgar_score, container, false);

        activity = view.findViewById(R.id.editAct);
        pulse = view.findViewById(R.id.editPulseRate);
        grim = view.findViewById(R.id.editGrim);
        look = view.findViewById(R.id.editAppearance);
        breathe = view.findViewById(R.id.editRespiration);

        textView = view.findViewById(R.id.apgarScore);
        calc = view.findViewById(R.id.btnCalcapgar);

        chkNA = view.findViewById( R.id.notApplicableCheckBox );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(activity.getText().toString().isEmpty()) && !(activity.getText().toString().isEmpty()) && !(pulse.getText().toString().isEmpty()) && !(grim.getText().toString().isEmpty()) && !(look.getText().toString().isEmpty()) && !(breathe.getText().toString().isEmpty())){
                    int score = Integer.parseInt(activity.getText().toString())+Integer.parseInt(pulse.getText().toString())+Integer.parseInt(grim.getText().toString())+Integer.parseInt(look.getText().toString())+Integer.parseInt(breathe.getText().toString());
                    String answer = "APGAR Score: "+score;
                    textView.setText(answer);
                }else{
                    Looper.prepare();
                    Toast.makeText(getContext(),"Please Fill In All the Fields", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });


        return view;
    }

    public JSONObject createJson(){
        apgarScore = new JSONObject();
        try{
            if(!chkNA.isChecked() && validate()) {
                apgarScore.put("Apgar Score", textView.getText().toString());
            }else if(chkNA.isChecked()){
                apgarScore.put("Status","Not applicable");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return apgarScore;
    }


    public boolean validate(){
        boolean valid = true;
        Looper.prepare();
        if(textView.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"APGAR Score: Please ensure that calculate has been pressed", Toast.LENGTH_SHORT).show();
        }
        Looper.loop();
        return valid;

    }


}
