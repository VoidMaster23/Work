package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ambulance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
    }

    public void ILS(View v){
        Intent i = new Intent(getApplicationContext(), ILSAMB.class);
        startActivity(i);
    }

    public void RV(View v){
        Intent i = new Intent(getApplicationContext(), ALSRV.class);
        startActivity(i);
    }

    public void ICU(View v){
        Intent i = new Intent(getApplicationContext(), ALS_ICU_AMB.class);
        startActivity(i);
    }

    public void PAED(View v){
        Intent i = new Intent(getApplicationContext(), ALSAMB_PAED.class);
        startActivity(i);
    }


}
