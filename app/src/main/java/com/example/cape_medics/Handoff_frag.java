package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONObject;

public class Handoff_frag extends Fragment {

    EditText destination,name,quality,service;
    JSONObject handover;

    public Handoff_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_handoff_frag, container, false);
        handover = new JSONObject();

        destination = view.findViewById(R.id.destinationEdit);
        name = view.findViewById(R.id.nameEdit);
        quality = view.findViewById(R.id.regEdit);
        service = view.findViewById(R.id.serviceEdit);

        return view;

    }

    public JSONObject createJson (){
        handover = new JSONObject();
        try{
            handover.put("Destination", destination.toString());
            handover.put("Name", name.toString());
            handover.put("Quality and Reg No.", quality.toString());
            handover.put("Service", service.toString());
        }catch(Exception e){}
        return handover;
    }

    public void Go (View v){

    }

    public void skip (View v){
        //skip this one
    }
}
