package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.json.JSONObject;

public class Mobility extends Fragment {
    CheckBox stretcher, wheelchair, walking, crutches;
    CheckBox neck, scoop, spinal, vacuum;
    String transportMethod, supportDevice;
    JSONObject mobility;

    public Mobility() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mobility, container, false);

        mobility = new JSONObject();

        //deal with this in send
        stretcher = view.findViewById(R.id.stretcherChk);
        wheelchair = view.findViewById(R.id.wheelchairChk);
        walking = view.findViewById(R.id.walkingChk);
        crutches = view.findViewById(R.id.crutchesChk);

        //deal with this in onclick
        neck = view.findViewById(R.id.neckChk);
        scoop = view.findViewById(R.id.scoopChk);
        spinal = view.findViewById(R.id.spinalChk);
        vacuum = view.findViewById(R.id.vacuumChk);

        stretcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()) {
                    wheelchair.setChecked(false);
                }
                if (walking.isChecked()) {
                    walking.setChecked(false);
                }
                if (crutches.isChecked()) {
                    crutches.setChecked(false);
                }
            }
        });

        wheelchair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stretcher.isChecked()) {
                    stretcher.setChecked(false);
                }
                if (walking.isChecked()) {
                    walking.setChecked(false);
                }
                if (crutches.isChecked()) {
                    crutches.setChecked(false);
                }
            }
        });

        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()) {
                    wheelchair.setChecked(false);
                }
                if (stretcher.isChecked()) {
                    stretcher.setChecked(false);
                }
                if (crutches.isChecked()) {
                    crutches.setChecked(false);
                }
            }
        });

        crutches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wheelchair.isChecked()) {
                    wheelchair.setChecked(false);
                }
                if (walking.isChecked()) {
                    walking.setChecked(false);
                }
                if (stretcher.isChecked()) {
                    stretcher.setChecked(false);
                }
            }
        });
        return view;
    }

    public void skip(View v) {

    }

    public JSONObject Send(View v) {
        mobility = new JSONObject();
        if (stretcher.isChecked()) transportMethod = stretcher.getText().toString();
        if (wheelchair.isChecked()) transportMethod = wheelchair.getText().toString();
        if (walking.isChecked()) transportMethod = walking.getText().toString();
        if (crutches.isChecked()) transportMethod = crutches.getText().toString();

        if (neck.isChecked()) supportDevice = neck.getText().toString();
        if (scoop.isChecked()) supportDevice = scoop.getText().toString();
        if (spinal.isChecked()) supportDevice = spinal.getText().toString();
        if (vacuum.isChecked()) supportDevice = vacuum.getText().toString();

        try {
            mobility.put("Support Device", supportDevice);
            mobility.put("Transport Method", transportMethod);
        } catch (Exception e) {
            return mobility;
        }
        return mobility;
    }
}
