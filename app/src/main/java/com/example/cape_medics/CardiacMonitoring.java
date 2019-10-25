package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class CardiacMonitoring extends Fragment {
    private ImageView imageView18;
    public CheckBox chkNA;
    private TextView textView13;
    private CheckBox chk3Lead;
    private CheckBox chk5Lead;
    private CheckBox chk12Lead;
    private CheckBox chkRhythm;
    private CheckBox chkCardio;
    private CheckBox chkPacing;
    JSONObject cardiacMonitoring;



    public CardiacMonitoring() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cardiac_monitoring, container, false);
        imageView18 = (ImageView)view.findViewById( R.id.imageView18 );
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        textView13 = (TextView)view.findViewById( R.id.textView13 );
        chk3Lead = (CheckBox)view.findViewById( R.id.chk3Lead );
        chk5Lead = (CheckBox)view.findViewById( R.id.chk5Lead );
        chk12Lead = (CheckBox)view.findViewById( R.id.chk12Lead );
        chkRhythm = (CheckBox)view.findViewById( R.id.chkRhythm );
        chkCardio = (CheckBox)view.findViewById( R.id.chkCardio );
        chkPacing = (CheckBox)view.findViewById( R.id.chkPacing );

        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });
        return view;
    }

    public JSONObject createJson(){
        String monitor  = null;
        cardiacMonitoring = new JSONObject();
        if(chk3Lead.isChecked()){
            monitor = chk3Lead.getText().toString();
        }else if(chk5Lead.isChecked()){
            monitor = chk5Lead.getText().toString();
        }else if(chk12Lead.isChecked()){
            monitor = chk12Lead.getText().toString();
        }
        else if(chkRhythm.isChecked()){
            monitor = chkRhythm.getText().toString();
        }else if(chkCardio.isChecked()){
            monitor = chkCardio.getText().toString();
        }
        else if(chkPacing.isChecked()){
            monitor = chkPacing.getText().toString();
        }


        try{
            cardiacMonitoring.put("Cardiac Monitoring",monitor);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cardiacMonitoring;

    }


}
