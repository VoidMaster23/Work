package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class CrewDetails extends Fragment {
    Button go;
    JSONObject crewDetails;
    EditText crew1, hpcsa1, crew2, hpcsa2, crew3, hpcsa3;
    public CrewDetails (){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_crew_details,container,false);
        crewDetails = new JSONObject();
        crew1 = view.findViewById(R.id.crew1Edit);
        hpcsa1 = view.findViewById(R.id.hpcsa1Edit);
        crew2 = view.findViewById(R.id.crew2Edit);
        hpcsa2 = view.findViewById(R.id.hpcsa2Edit);
        crew3 = view.findViewById(R.id.crew3Edit);
        hpcsa3 = view.findViewById(R.id.hpcsa3Edit);

        go = view.findViewById(R.id.signatureButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Crew Details ");
                startActivity(i);
            }
        });

        return view;
    }

    public JSONObject Send (View v){
        crewDetails = new JSONObject();
        try{
            crewDetails.put("Crew 1", crew1);
            crewDetails.put("Crew 2", crew2);
            crewDetails.put("Crew 3", crew3);

            crewDetails.put("HPCSA No. 1", hpcsa1);
            crewDetails.put("HPCSA No. 2", hpcsa2);
            crewDetails.put("HPCSA No. 3", hpcsa3);

        }catch(Exception e){}

        return crewDetails;
    }
}
