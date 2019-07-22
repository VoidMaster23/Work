package com.example.cape_medics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FireSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_select);
    }

    public void Engine(View v){
        Intent i = new Intent(getApplicationContext(), FireEngine.class);
        startActivity(i);
    }

    public void Skid(View v){
        Intent i = new Intent(getApplicationContext(), FireSkid.class);
        startActivity(i);
    }
}
