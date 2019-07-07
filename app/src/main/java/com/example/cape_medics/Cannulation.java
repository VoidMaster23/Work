package com.example.cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;


public class Cannulation extends Fragment {
private ExpandableRelativeLayout expLay1, expLay2;
private Button button1, button2;
private Spinner spn1,spn2;

private String[] fluid = {"Saline 0.9%","Ringers Lactate","Colloids","Plasma","Whole Blood"};


    public Cannulation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cannulation, container, false);

        expLay1 = view.findViewById(R.id.canLay1);
        expLay2 = view.findViewById(R.id.canLay2);

        button1 = view.findViewById(R.id.btnCan1);
        button2 = view.findViewById(R.id.btnCan2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expLay1.toggle();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expLay2.toggle();
            }
        });

        spn1 = view.findViewById(R.id.canSpin1);
        spn2 = view.findViewById(R.id.canSpin2);

        ArrayAdapter a1 = new ArrayAdapter(getContext(),R.layout.custom_spinner,fluid);
        ArrayAdapter a2 = new ArrayAdapter(getContext(),R.layout.custom_spinner,fluid);


        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn1.setAdapter(a1);
        spn2.setAdapter(a2);



        return view;
    }


}
