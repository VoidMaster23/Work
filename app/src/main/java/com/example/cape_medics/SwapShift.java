package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class SwapShift extends AppCompatActivity {
    JSONObject swapShift;
    EditText date;
    EditText name;
    CheckBox early;
    CheckBox late;
    String jobName;
    boolean earlyOrlate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        swapShift = new JSONObject();

        date = findViewById(R.id.editText);
        name = findViewById(R.id.spinner);
        early = findViewById(R.id.checkBox);
        late = findViewById(R.id.checkBox2);
        Bundle bundle = getIntent().getExtras();
        jobName = bundle.getString("job");
    }

    public void Send (View v){
        //send information to to database i.e. job name, person who sent it and the persons motivation

        if(early.isChecked()){
            earlyOrlate = true;
            try {
                swapShift.put("Job_Name",jobName);
                swapShift.put("Name",name );
                swapShift.put("Date", date);
                swapShift.put("time",early.getText().toString());
            }catch(Exception e){}
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            startActivity(i);
            Toast.makeText(this, "Request Sent",Toast.LENGTH_LONG).show();
        }
        else if(late.isChecked()){
            earlyOrlate = false;
            try {
                swapShift.put("Job_Name",jobName);
                swapShift.put("Name",name );
                swapShift.put("Date", date);
                swapShift.put("time",late.getText().toString());
            }catch(Exception e){}
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            startActivity(i);
            Toast.makeText(this, "Request Sent",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please check one of the boxes",Toast.LENGTH_LONG).show();
        }

        try {
            swapShift.put("Name",name );
            swapShift.put("Date", date);
            swapShift.put("time",early.getText().toString());
        }catch(Exception e){}

    }

    public void Cancel (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }
    // make sure that both boxes arent checked
    public void uncheck(View v){
        if(v.getId() == R.id.checkBox){
            late.setChecked(false);
        }else{
            early.setChecked(false);
        }

    }
}
