package com.example.cape_medics;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    JSONObject dashBoard;
    JSONObject JobJson;
    JSONObject BroadcastJson;
    ListView CurrentJobs;
    ListView PastJobs;
    ListView Broadcasts;
    String jobs,broadcasts;

    List JobList;
    List BroadcastList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // will get arrays off database
        dashBoard = new JSONObject();

        Bundle bundle = getIntent().getExtras();
        broadcasts = bundle.getString("broadcasts");
        jobs = bundle.getString("jobs");
        JobList = new ArrayList<String>();
        BroadcastList = new ArrayList<String>();
        Log.e("broadcasts", "cake -----" + broadcasts);
        Log.e("jobs", "pie -----" + jobs);


        try {
            JobJson = new JSONObject(jobs);
            BroadcastJson = new JSONObject(broadcasts);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<String> Jobkeys = JobJson.keys();
        while(Jobkeys.hasNext()) {
            String key = Jobkeys.next();
            try {
                String item = JobJson.getString(key);
                JobList.add(key);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.e("jobs", JobList.toString());
        Iterator<String> Broadcastkeys = BroadcastJson.keys();
        while(Broadcastkeys.hasNext()) {
            String key = Broadcastkeys.next();
            try {
                String item = BroadcastJson.getString(key);
                BroadcastList.add(item);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //retrieve these lists
        //String[] currentArray = {"Current Job 1","d","d","d","d","d","d", "Current Job 2"} ;
        String[] pastArray ={"Past Job 1", "Past Job 2"} ;
        //String[] broadcastArray ={"Broadcast 1", "Broadcast 2 rwerewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"};

        CurrentJobs = findViewById(R.id.Current_Jobs);
        PastJobs = findViewById(R.id.Past_Jobs);
        Broadcasts = findViewById(R.id.broadcastList);

        ArrayAdapter<String> currentJobs = new ArrayAdapter<String>(this, R.layout.list_row,JobList);
        ArrayAdapter<String> pastJobs = new ArrayAdapter<String>(this, R.layout.list_row,pastArray);
        ArrayAdapter<String> broadcasts = new ArrayAdapter<String>(this, R.layout.list_row,BroadcastList);

        CurrentJobs.setAdapter(currentJobs);
        PastJobs.setAdapter(pastJobs);
        Broadcasts.setAdapter(broadcasts);


        CurrentJobs.setOnItemClickListener((parent, view, position, id) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Swap Shift")
                    .setMessage("Do you want to swap this job shift with someone?")
                    .setPositiveButton("No", (dialog, which) -> dialog.cancel())
                    .setNegativeButton("Yes", (dialog, which) -> {
                        Intent i = new Intent(getApplicationContext(), SwapShift.class);
                        i.putExtra("job", String.valueOf(CurrentJobs.getItemAtPosition(position)));
                        startActivity(i);
                    })
                    .show();
        });

        //json stuff broad cast

    }

}
