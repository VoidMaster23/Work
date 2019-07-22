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

public class Notes extends Fragment {

    EditText challenges,delays,comments,QAcomments;
    JSONObject notes;

    public Notes(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_notes, container, false);
        notes = new JSONObject();
        challenges = view.findViewById(R.id.challengesEdit);
        delays = view.findViewById(R.id.delaysEdit);
        comments = view.findViewById(R.id.otherEdit);
        QAcomments = view.findViewById(R.id.commentsEdit);

        return view;
    }

    public void skip (View v){
        //send it as this is last one
        Intent i = new Intent(getContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    public JSONObject Send (View v){
        notes = new JSONObject();
        try{
            notes.put("Challenges", challenges.toString());
            notes.put("Delays", delays.toString());
            notes.put("Other Comments", comments.toString());
            notes.put("QA Comments", QAcomments.toString());

        }catch(Exception e){}

       // Intent i = new Intent(getContext(), Home_Screen_Crew.class);
       // startActivity(i);
        return  notes;
    }
}
