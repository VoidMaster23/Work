package com.example.cape_medics;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BroadCast extends AppCompatActivity {
    JSONObject broadcast;
    ListView crewName;
    CheckBox selectAll;
    List<String> Names;
    String jobName;
    Dialog popup;
    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        broadcast = new JSONObject();


        crewName = findViewById(R.id.listView);
        selectAll = findViewById(R.id.checkBox);
        Names = new ArrayList<String>();

        Bundle bundle = getIntent().getExtras();
        //use this to pull list from db
        jobName = bundle.getString("job");
        crewName.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] CrewName = {"Bob", "Jim", "Carrol"}; // pull from db

        ArrayAdapter<String> CrewNameAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_checked_list,CrewName);

        crewName.setAdapter(CrewNameAdapter);

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAll.isChecked()) {

                    for (int i = 0; i < crewName.getChildCount(); i++) {
                        crewName.setItemChecked(i, true);
                    }
                }
                else{
                    for (int i = 0; i < crewName.getChildCount(); i++) {
                        crewName.setItemChecked(i, false);
                    }
                }

            }
        });
    }

    public void Choose (View v){
        //send to db
        for (int i = 0; i < crewName.getChildCount(); i++) {

            if (crewName.isItemChecked(i)){
                Names.add(crewName.getItemAtPosition(i).toString());
            }
        }


        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, getResources().getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 475, getResources().getDisplayMetrics());
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (int)width, (int)height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // dismiss the popup window when X touched
        popupView.findViewById(R.id.txtclose).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        message = popupView.findViewById(R.id.message);

        //send the message
        popupView.findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send from here
                for (int i=0;i<Names.size();i++){
                    try {
                        broadcast.put(Names.get(i), message.toString());
                    }catch(Exception e){}
                }
                Toast.makeText(getApplicationContext(),"Message Sent!", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        //send Names array list to other activity

        //Intent i = new Intent(getApplicationContext(), BroadCastMessage.class);
        // startActivity(i);


    }

    public void Cancel (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }





}
