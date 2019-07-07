package com.example.cape_medics;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;


public class DrugsAdministered extends Fragment {
  private   ExpandableRelativeLayout drug1, drug2,drug3,drug4, drug5,drug6;
  private Button but1, but2, but3, but4, but5, but6;
  private Spinner drugspn1,drugspn2,drugspn3,drugspn4,drugspn5,drugspn6;
  String[] drugs;


    public DrugsAdministered() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_drugs_administered, container, false);

        drug1 = view.findViewById(R.id.drugLay1);
        drug2 = view.findViewById(R.id.drugLay2);
        drug3 = view.findViewById(R.id.drugLay3);
        drug4 = view.findViewById(R.id.drugLay4);
        drug5 = view.findViewById(R.id.drugLay5);
        drug6 = view.findViewById(R.id.drugLay6);

        but1 = view.findViewById(R.id.btnDrugs1);
        but2 = view.findViewById(R.id.btnDrugs2);
        but3 = view.findViewById(R.id.btnDrugs3);
        but4 = view.findViewById(R.id.btnDrugs4);
        but5 = view.findViewById(R.id.btnDrugs5);
        but6 = view.findViewById(R.id.btnDrugs6);

        drugspn1 = view.findViewById(R.id.drugNameSpn1);
        drugspn2 = view.findViewById(R.id.drugNameSpn2);
        drugspn3 = view.findViewById(R.id.drugNameSpn3);
        drugspn4 = view.findViewById(R.id.drugNameSpn4);
        drugspn5 = view.findViewById(R.id.drugNameSpn5);
        drugspn6 = view.findViewById(R.id.drugNameSpn6);

        String filename = null;
        try {

            filename = "drugs.txt";
            InputStream in = getContext().getAssets().open(filename, AssetManager.ACCESS_BUFFER);
            String sHTML = StreamToString(in);
            in.close();
           // FileData data = new FileData(sHTML);
            drugs = sHTML.split("\n");
            ArrayAdapter adapter1 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter adapter2 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter adapter3 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter adapter4 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter adapter5 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter adapter6 = new ArrayAdapter(getContext(),R.layout.custom_spinner,drugs);
            adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            drugspn1.setAdapter(adapter1);
            drugspn2.setAdapter(adapter2);
            drugspn3.setAdapter(adapter3);
            drugspn4.setAdapter(adapter4);
            drugspn5.setAdapter(adapter5);
            drugspn6.setAdapter(adapter6);
        }catch (IOException e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }





        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug1.toggle();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug2.toggle();
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug3.toggle();
            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug4.toggle();
            }
        });

        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug5.toggle();
            }
        });

        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug6.toggle();
            }
        });

        return view;
    }

    public static String StreamToString(InputStream in) throws IOException {
        if(in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }

}
