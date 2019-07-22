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
import android.widget.Toast;

import org.json.JSONObject;

public class DashBoard extends AppCompatActivity {
    JSONObject dashBoard;
    ListView CurrentJobs;
    ListView PastJobs;
    ListView Broadcasts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // will get arrays off database
        dashBoard = new JSONObject();

        //retrieve these lists
        String[] currentArray = {"Current Job 1","d","d","d","d","d","d", "Current Job 2"} ;
        String[] pastArray ={"Past Job 1", "Past Job 2"} ;
        String[] broadcastArray ={"Broadcast 1", "Broadcast 2 rwerewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"};

        CurrentJobs = findViewById(R.id.Current_Jobs);
        PastJobs = findViewById(R.id.Past_Jobs);
        Broadcasts = findViewById(R.id.broadcastList);

        ArrayAdapter<String> currentJobs = new ArrayAdapter<String>(this, R.layout.list_row,currentArray);
        ArrayAdapter<String> pastJobs = new ArrayAdapter<String>(this, R.layout.list_row,pastArray);
        ArrayAdapter<String> broadcasts = new ArrayAdapter<String>(this, R.layout.list_row,broadcastArray);

        CurrentJobs.setAdapter(currentJobs);
        PastJobs.setAdapter(pastJobs);
        Broadcasts.setAdapter(broadcasts);


        CurrentJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Swap Shift")
                        .setMessage("Do you want to swap this job shift with someone?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), SwapShift.class);
                                i.putExtra("job", String.valueOf(CurrentJobs.getItemAtPosition(position)));
                                startActivity(i);
                            }
                        }).show();
            }
        });

        //json stuff broad cast

    }

}
