package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONObject;


public class ApgarScore extends Fragment {
    EditText activity, pulse, grim, look, breathe;
    Button calc;
    TextView textView;

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

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(activity.getText().toString().isEmpty()) && !(activity.getText().toString().isEmpty()) && !(pulse.getText().toString().isEmpty()) && !(grim.getText().toString().isEmpty()) && !(look.getText().toString().isEmpty()) && !(breathe.getText().toString().isEmpty())){
                    int score = Integer.parseInt(activity.getText().toString())+Integer.parseInt(pulse.getText().toString())+Integer.parseInt(grim.getText().toString())+Integer.parseInt(look.getText().toString())+Integer.parseInt(breathe.getText().toString());
                    String answer = "APGAR Score: "+score;
                    textView.setText(answer);
                }else{
                    Toast.makeText(getContext(),"Please Fill In All the Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    public JSONObject createJson(){
        apgarScore = new JSONObject();
        try{
            apgarScore.put("Apgar Score",textView.getText().toString());
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return apgarScore;
    }



}
