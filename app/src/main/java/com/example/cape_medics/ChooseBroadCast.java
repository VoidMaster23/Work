package com.example.cape_medics;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ChooseBroadCast extends AppCompatActivity {

    Spinner jobType;
    Spinner unitType;
    ListView Jobs;
    String job;
    String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        jobType = findViewById(R.id.spinner9);
        unitType = findViewById(R.id.spinner10);
        Jobs = findViewById(R.id.listView);

        String[] JobType = {"FILM","EVENT","COMMERCIAL","REHEARSAL","RIGGING","TRAINING","OFFICE STAFF"};
        String[] UnitType = {"MAIN UNIT","2ND UNIT","SPLINTER UNIT","REHEARSAL","RIGGING","CONSTRUCTION",
                "OFFICE STAFF","BUILD UP","STRIKE","RECCE","SAFETY INSPECTION","ACCIDENT INVESTIGATION",
                "AIR MONITORING","EAP","RISK ASSESSMENT"};

        ArrayAdapter<String> JobTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,JobType);
        ArrayAdapter<String> UnitTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,UnitType);

        JobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jobType.setAdapter(JobTypeAdapter);
        unitType.setAdapter(UnitTypeAdapter);



        //fill from db and have them change on spinner change
        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                job = String.valueOf(jobType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                job = "";
            }
        });

        unitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = String.valueOf(unitType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                unit = "";
            }
        });

        //send over unit and job strings to db and get list of current jobs

        String[] currentJobs = {"Job 1", "Job 2"};



        ArrayAdapter<String> CurrentJobs = new ArrayAdapter<String>(this, R.layout.list_row,currentJobs);
        Jobs.setAdapter(CurrentJobs);

        Jobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Crew Member selection")
                        .setMessage("Do you wish to see the crew members of this job?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), BroadCast.class);
                                i.putExtra("job", String.valueOf(Jobs.getItemAtPosition(position)));
                                startActivity(i);
                            }
                        }).show();
            }
        });

    }
}
