package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONObject;

public class Notes extends Fragment {

    EditText challenges,delays,comments,QAcomments;
    JSONObject notes;
    CheckBox chkNA;

    public Notes(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_notes, container, false);
        //notes = new JSONObject();
        challenges = view.findViewById(R.id.challengesEdit);
        delays = view.findViewById(R.id.delaysEdit);
        comments = view.findViewById(R.id.otherEdit);
        QAcomments = view.findViewById(R.id.commentsEdit);

        chkNA = view.findViewById(R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(view1 -> {
            medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);
        });

        return view;
    }

    public JSONObject createJson (){
        notes = new JSONObject();
        try{
            if(!chkNA.isChecked()) {
                notes.put("Challenges", challenges.getText().toString());
                notes.put("Delays", delays.getText().toString());
                notes.put("Other Comments", comments.getText().toString());
                notes.put("QA Comments", QAcomments.getText().toString());
            }else if(chkNA.isChecked()){
                notes.put("Status","Not applicable");
            }
        }catch(Exception e){}

       // Intent i = new Intent(getContext(), Home_Screen_Crew.class);
       // startActivity(i);
        return  notes;
    }
}
