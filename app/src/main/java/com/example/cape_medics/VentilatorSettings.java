package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONObject;

public class VentilatorSettings extends Fragment {
    JSONObject ventilatorSettings;

    EditText lpm, lpm1, lpm2, tidal, tidal1, tidal2, tv, tv1, tv2, ventMode, ventMode1, ventMode2,ventRate, ventRate1, ventRate2,peep,peep1,peep2,ratio,ratio1,ratio2;
    EditText lpmTime,lpmTime1,lpmTime2,tidalTime,tidalTime1,tidalTime2,tvTime,tvTime1,tvTime2,ventModeTime,ventModeTime1,ventModeTime2,ventRateTime,ventRateTime1,ventRateTime2,peepTime,peepTime1,peepTime2,ratioTime,ratioTime1,ratioTime2;

    public VentilatorSettings(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ventilator_settings,container,false);
        lpm = view.findViewById(R.id.lpmEdit);
        lpm1 = view.findViewById(R.id.lpmEdit1);
        lpm2 = view.findViewById(R.id.lpmEdit2);
        lpmTime = view.findViewById(R.id.lpmTime);
        lpmTime1 = view.findViewById(R.id.lpmTime1);
        lpmTime2 = view.findViewById(R.id.lpmTime2);

        tidal = view.findViewById(R.id.tidalEdit);
        tidal1 = view.findViewById(R.id.tidalEdit1);
        tidal2 = view.findViewById(R.id.tidalEdit2);
        tidalTime = view.findViewById(R.id.tidalTime);
        tidalTime1 = view.findViewById(R.id.tidalTime1);
        tidalTime2 = view.findViewById(R.id.tidalTime2);

        tv = view.findViewById(R.id.spontaneousEdit);
        tv1 = view.findViewById(R.id.spontaneousEdit1);
        tv2 = view.findViewById(R.id.spontaneousEdit2);
        tvTime = view.findViewById(R.id.spontaneousTime);
        tvTime1 = view.findViewById(R.id.spontaneousTime1);
        tvTime2 = view.findViewById(R.id.spontaneousTime2);

        ventMode = view.findViewById(R.id.ventEdit);
        ventMode1 = view.findViewById(R.id.ventEdit1);
        ventMode2 = view.findViewById(R.id.ventEdit2);
        ventModeTime = view.findViewById(R.id.ventTime);
        ventModeTime1 = view.findViewById(R.id.ventTime1);
        ventModeTime2 = view.findViewById(R.id.ventTime2);

        ventRate = view.findViewById(R.id.rateEdit);
        ventRate1 = view.findViewById(R.id.rateEdit1);
        ventRate2 = view.findViewById(R.id.rateEdit2);
        ventRateTime = view.findViewById(R.id.rateTime);
        ventRateTime1 = view.findViewById(R.id.rateTime1);
        ventRateTime2 = view.findViewById(R.id.rateTime2);

        peep = view.findViewById(R.id.peepEdit);
        peep1 = view.findViewById(R.id.peepEdit1);
        peep2 = view.findViewById(R.id.peepEdit2);
        peepTime = view.findViewById(R.id.peepTime);
        peepTime1 = view.findViewById(R.id.peepTime1);
        peepTime2 = view.findViewById(R.id.peepTime2);

        ratio = view.findViewById(R.id.ratioEdit);
        ratio1 = view.findViewById(R.id.ratioEdit1);
        ratio2 = view.findViewById(R.id.ratioEdit2);
        ratioTime = view.findViewById(R.id.ratioTime);
        ratioTime1 = view.findViewById(R.id.ratioTime1);
        ratioTime2 = view.findViewById(R.id.ratioTime2);


        return view;
    }

    public JSONObject createJson(){
        ventilatorSettings = new JSONObject();
        try{
            ventilatorSettings.put("Fi02/LPM - First Measurement", lpm.toString());
            ventilatorSettings.put("Fi02/LPM - First Time Reading", lpmTime.toString());
            ventilatorSettings.put("Fi02/LPM - Second Measurement", lpm1.toString());
            ventilatorSettings.put("Fi02/LPM - Second Time Reading", lpmTime1.toString());
            ventilatorSettings.put("Fi02/LPM - Third Measurement", lpm2.toString());
            ventilatorSettings.put("Fi02/LPM - Third Time Reading", lpmTime2.toString());

            ventilatorSettings.put("Tidal Volume - First Measurement", tidal.toString());
            ventilatorSettings.put("Tidal Volume  - First Time Reading", tidalTime.toString());
            ventilatorSettings.put("Tidal Volume  - Second Measurement", tidal1.toString());
            ventilatorSettings.put("Tidal Volume  - Second Time Reading", tidalTime1.toString());
            ventilatorSettings.put("Tidal Volume  - Third Measurement", tidal2.toString());
            ventilatorSettings.put("Tidal Volume  - Third Time Reading", tidalTime2.toString());

            ventilatorSettings.put("Spontaneous TV - First Measurement", tv.toString());
            ventilatorSettings.put("Spontaneous TV - First Time Reading", tvTime.toString());
            ventilatorSettings.put("Spontaneous TV- Second Measurement", tv1.toString());
            ventilatorSettings.put("Spontaneous TV - Second Time Reading", tvTime1.toString());
            ventilatorSettings.put("Spontaneous TV - Third Measurement", tv2.toString());
            ventilatorSettings.put("Spontaneous TV - Third Time Reading", tvTime2.toString());

            ventilatorSettings.put("Vent Mode - First Measurement", ventMode.toString());
            ventilatorSettings.put("Vent Mode - First Time Reading", ventModeTime.toString());
            ventilatorSettings.put("Vent Mode - Second Measurement", ventMode1.toString());
            ventilatorSettings.put("Vent Mode - Second Time Reading", ventModeTime1.toString());
            ventilatorSettings.put("Vent Mode - Third Measurement", ventMode2.toString());
            ventilatorSettings.put("Vent Mode - Third Time Reading", ventModeTime2.toString());

            ventilatorSettings.put("PEEP- First Measurement", peep.toString());
            ventilatorSettings.put("PEEP - First Time Reading", peepTime.toString());
            ventilatorSettings.put("PEEP - Second Measurement", peep1.toString());
            ventilatorSettings.put("PEEP - Second Time Reading", peepTime1.toString());
            ventilatorSettings.put("PEEP - Third Measurement", peep2.toString());
            ventilatorSettings.put("PEEP - Third Time Reading", peepTime2.toString());

            ventilatorSettings.put("I.E. Ratio - First Measurement", ratio.toString());
            ventilatorSettings.put("I.E. Ratio - First Time Reading", ratioTime.toString());
            ventilatorSettings.put("I.E. Ratio - Second Measurement", ratio1.toString());
            ventilatorSettings.put("I.E. Ratio - Second Time Reading", ratioTime1.toString());
            ventilatorSettings.put("I.E. Ratio - Third Measurement", ratio2.toString());
            ventilatorSettings.put("I.E. Ratio- Third Time Reading", ratioTime2.toString());


        }catch(Exception e){}

        return ventilatorSettings;
    }
}
