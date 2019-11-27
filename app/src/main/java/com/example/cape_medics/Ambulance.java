package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ambulance extends AppCompatActivity {
    String code,authorisation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
        authorisation = bundle.getString("Authorisation");
    }

    public void ILS(View v){
        Intent i = new Intent(getApplicationContext(), ILSAMB.class);
        i.putExtra("code",code);
        i.putExtra("Authorisation",authorisation);
        startActivity(i);
    }

    public void RV(View v){
        Intent i = new Intent(getApplicationContext(), ALSRV.class);
        i.putExtra("code",code);
        i.putExtra("Authorisation",authorisation);
        startActivity(i);
    }

    public void ICU(View v){
        Intent i = new Intent(getApplicationContext(), ALS_ICU_AMB.class);
        i.putExtra("code",code);
        i.putExtra("Authorisation",authorisation);
        startActivity(i);
    }

    public void PAED(View v){
        Intent i = new Intent(getApplicationContext(), ALSAMB_PAED.class);
        i.putExtra("code",code);
        i.putExtra("Authorisation",authorisation);
        startActivity(i);
    }


}
