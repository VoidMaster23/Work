package com.example.cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;


public class VitalSigns extends Fragment {
 ExpandableRelativeLayout expandableRelativeLayout1, expandableRelativeLayout2,expandableRelativeLayout3, expandableRelativeLayout4;
Button button1,button2,button3, button4;
    public VitalSigns() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view = inflater.inflate(R.layout.fragment_vital_signs, container, false);
        expandableRelativeLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expLay1);
        expandableRelativeLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expLay2);
        expandableRelativeLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expLay3);
        expandableRelativeLayout4 = (ExpandableRelativeLayout) view.findViewById(R.id.expLay4);

        button1 = view.findViewById(R.id.expBut1);
        button2 = view.findViewById(R.id.expBut2);
        button3 = view.findViewById(R.id.expBut3);
        button4 = view.findViewById(R.id.expBut4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout1.toggle();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout2.toggle();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout3.toggle();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout4.toggle();
            }
        });

     return view;
    }





}
