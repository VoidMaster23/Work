package com.example.cape_medics;

import android.content.Intent;
import android.os.Looper;
import android.preference.CheckBoxPreference;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class CrewDetails extends Fragment {
    Button go;
    JSONObject crewDetails;
    EditText crew1, hpcsa1, crew2, hpcsa2, crew3, hpcsa3;
    CheckBox chkNA;

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
        chkNA = view.findViewById( R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

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

    public JSONObject createJson (){
        crewDetails = new JSONObject();
        try{
            if(!chkNA.isChecked() && validate()) {
                crewDetails.put("Crew 1", crew1.getText().toString());
                crewDetails.put("Crew 2", crew2.getText().toString());
                crewDetails.put("Crew 3", crew3.getText().toString());

                crewDetails.put("HPCSA No. 1", hpcsa1.getText().toString());
                crewDetails.put("HPCSA No. 2", hpcsa2.getText().toString());
                crewDetails.put("HPCSA No. 3", hpcsa3.getText().toString());
            }else if(chkNA.isChecked()){
                crewDetails.put("Status","Not Applicable");
            }
        }catch(Exception e){}

        return crewDetails;
    }

    public boolean validate(){
        boolean valid = false;
        Looper.prepare();
        if (crew1.getText().toString().isEmpty()) {
            valid = false;
            Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
        }
        if(valid){
            if (crew2.getText().toString().isEmpty()) {
                valid = false;
                Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
            }
        }
        if(valid){
            if (crew3.getText().toString().isEmpty()) {
                valid = false;
                Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if (hpcsa1.getText().toString().isEmpty()) {
                valid = false;
                Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if (hpcsa2.getText().toString().isEmpty()) {
                valid = false;
                Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if (hpcsa3.getText().toString().isEmpty()) {
                valid = false;
                Toast.makeText(getContext(),"Crew Details: Please Enter all the crew details",Toast.LENGTH_SHORT).show();
            }
        }
        Looper.loop();
        return valid;
    }
}
