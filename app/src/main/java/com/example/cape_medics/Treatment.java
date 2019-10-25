package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONObject;

public class Treatment extends Fragment {
    EditText examination, otherProviders, diagnosis;
    JSONObject treatment;
    CheckBox chkNA;
    public Treatment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_treatment,container,false);
        treatment = new JSONObject();
        examination = view.findViewById(R.id.examinationEdit);
        otherProviders = view.findViewById(R.id.otherEdit);
        diagnosis = view.findViewById(R.id.diagnosisEdit);
        chkNA = view.findViewById( R.id.notApplicableCheckBox);
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
        treatment = new JSONObject();
        try {
            treatment.put("Examination", examination.toString());
            treatment.put("Treatment by Other Providers", otherProviders.toString());
            treatment.put("Diagnosis", diagnosis.toString());

        }catch(Exception e){}

        return treatment;
    }
}
