package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VehicleCheckList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_check_list);
    }

    public void Amb(View v){
        Intent i = new Intent(getApplicationContext(), Ambulance.class);
        startActivity(i);
    }

    public void Fire(View v){
        Intent i = new Intent(getApplicationContext(), FireEngine.class);
        startActivity(i);
    }
}
