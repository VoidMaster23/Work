package com.example.cape_medics;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONObject;

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
    private ImageView imageView18;
    private CheckBox chkNA;
    private ScrollView drugScrl1;
    private TextView drugTime1;
    private EditText edtDrugTime1;
    private TextView drugName1;
    private TextView drugDose1;
    private EditText edtDrugDose1;
    private TextView drugRoute1;
    private EditText edtDrugRoute1;
    private TextView drugGiven1;
    private EditText edtDrugGiven1;
    private ScrollView drugScrl2;
    private TextView drugTime2;
    private EditText edtDrugTime2;
    private TextView drugName2;
    private TextView drugDose2;
    private EditText edtDrugDose2;
    private TextView drugRoute2;
    private EditText edtDrugRoute2;
    private TextView drugGiven2;
    private EditText edtDrugGiven2;
    private ScrollView drugScrl3;
    private TextView drugTime3;
    private EditText edtDrugTime3;
    private TextView drugName3;
    private TextView drugDose3;
    private EditText edtDrugDose3;
    private TextView drugRoute3;
    private EditText edtDrugRoute3;
    private TextView drugGiven3;
    private EditText edtDrugGiven3;
    private ScrollView drugScrl4;
    private TextView drugTime4;
    private EditText edtDrugTime4;
    private TextView drugName4;
    private TextView drugDose4;
    private EditText edtDrugDose4;
    private TextView drugRoute4;
    private EditText edtDrugRoute4;
    private TextView drugGiven4;
    private EditText edtDrugGiven4;
    private ScrollView drugScrl5;
    private TextView drugTime5;
    private EditText edtDrugTime5;
    private TextView drugName5;
    private TextView drugDose5;
    private EditText edtDrugDose5;
    private TextView drugRoute5;
    private EditText edtDrugRoute5;
    private TextView drugGiven5;
    private EditText edtDrugGiven5;
    private ScrollView drugScrl6;
    private TextView drugTime6;
    private EditText edtDrugTime6;
    private TextView drugName6;
    private TextView drugDose6;
    private EditText edtDrugDose6;
    private TextView drugRoute6;
    private EditText edtDrugRoute6;
    private TextView drugGiven6;
    private EditText edtDrugGiven6;


    String drugStr1;
    String drugStr2;
    String drugStr3;
    String drugStr4;
    String drugStr5;
    String drugStr6;

    JSONObject drugsAdministered;

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

        imageView18 = (ImageView)view.findViewById( R.id.imageView18 );
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        drugScrl1 = (ScrollView)view.findViewById( R.id.drugScrl1 );
        drugTime1 = (TextView)view.findViewById( R.id.drugTime1 );
        edtDrugTime1 = (EditText)view.findViewById( R.id.edtDrugTime1 );
        drugName1 = (TextView)view.findViewById( R.id.drugName1 );
        drugDose1 = (TextView)view.findViewById( R.id.drugDose1 );
        edtDrugDose1 = (EditText)view.findViewById( R.id.edtDrugDose1 );
        drugRoute1 = (TextView)view.findViewById( R.id.drugRoute1 );
        edtDrugRoute1 = (EditText)view.findViewById( R.id.edtDrugRoute1 );
        drugGiven1 = (TextView)view.findViewById( R.id.drugGiven1 );
        edtDrugGiven1 = (EditText)view.findViewById( R.id.edtDrugGiven1 );
        drugScrl2 = (ScrollView)view.findViewById( R.id.drugScrl2 );
        drugTime2 = (TextView)view.findViewById( R.id.drugTime2 );
        edtDrugTime2 = (EditText)view.findViewById( R.id.edtDrugTime2 );
        drugName2 = (TextView)view.findViewById( R.id.drugName2 );
        drugDose2 = (TextView)view.findViewById( R.id.drugDose2 );
        edtDrugDose2 = (EditText)view.findViewById( R.id.edtDrugDose2 );
        drugRoute2 = (TextView)view.findViewById( R.id.drugRoute2 );
        edtDrugRoute2 = (EditText)view.findViewById( R.id.edtDrugRoute2 );
        drugGiven2 = (TextView)view.findViewById( R.id.drugGiven2 );
        edtDrugGiven2 = (EditText)view.findViewById( R.id.edtDrugGiven2 );
        drugScrl3 = (ScrollView)view.findViewById( R.id.drugScrl3 );
        drugTime3 = (TextView)view.findViewById( R.id.drugTime3 );
        edtDrugTime3 = (EditText)view.findViewById( R.id.edtDrugTime3 );
        drugName3 = (TextView)view.findViewById( R.id.drugName3 );
        drugDose3 = (TextView)view.findViewById( R.id.drugDose3 );
        edtDrugDose3 = (EditText)view.findViewById( R.id.edtDrugDose3 );
        drugRoute3 = (TextView)view.findViewById( R.id.drugRoute3 );
        edtDrugRoute3 = (EditText)view.findViewById( R.id.edtDrugRoute3 );
        drugGiven3 = (TextView)view.findViewById( R.id.drugGiven3 );
        edtDrugGiven3 = (EditText)view.findViewById( R.id.edtDrugGiven3 );
        drugScrl4 = (ScrollView)view.findViewById( R.id.drugScrl4 );
        drugTime4 = (TextView)view.findViewById( R.id.drugTime4 );
        edtDrugTime4 = (EditText)view.findViewById( R.id.edtDrugTime4 );
        drugName4 = (TextView)view.findViewById( R.id.drugName4 );
        drugDose4 = (TextView)view.findViewById( R.id.drugDose4 );
        edtDrugDose4 = (EditText)view.findViewById( R.id.edtDrugDose4 );
        drugRoute4 = (TextView)view.findViewById( R.id.drugRoute4 );
        edtDrugRoute4 = (EditText)view.findViewById( R.id.edtDrugRoute4 );
        drugGiven4 = (TextView)view.findViewById( R.id.drugGiven4 );
        edtDrugGiven4 = (EditText)view.findViewById( R.id.edtDrugGiven4 );
        drugScrl5 = (ScrollView)view.findViewById( R.id.drugScrl5 );
        drugTime5 = (TextView)view.findViewById( R.id.drugTime5 );
        edtDrugTime5 = (EditText)view.findViewById( R.id.edtDrugTime5 );
        drugName5 = (TextView)view.findViewById( R.id.drugName5 );
        drugDose5 = (TextView)view.findViewById( R.id.drugDose5 );
        edtDrugDose5 = (EditText)view.findViewById( R.id.edtDrugDose5 );
        drugRoute5 = (TextView)view.findViewById( R.id.drugRoute5 );
        edtDrugRoute5 = (EditText)view.findViewById( R.id.edtDrugRoute5 );
        drugGiven5 = (TextView)view.findViewById( R.id.drugGiven5 );
        edtDrugGiven5 = (EditText)view.findViewById( R.id.edtDrugGiven5 );
        drugScrl6 = (ScrollView)view.findViewById( R.id.drugScrl6 );
        drugTime6 = (TextView)view.findViewById( R.id.drugTime6 );
        edtDrugTime6 = (EditText)view.findViewById( R.id.edtDrugTime6 );
        drugName6 = (TextView)view.findViewById( R.id.drugName6 );
        drugDose6 = (TextView)view.findViewById( R.id.drugDose6 );
        edtDrugDose6 = (EditText)view.findViewById( R.id.edtDrugDose6 );
        drugRoute6 = (TextView)view.findViewById( R.id.drugRoute6 );
        edtDrugRoute6 = (EditText)view.findViewById( R.id.edtDrugRoute6 );
        drugGiven6 = (TextView)view.findViewById( R.id.drugGiven6 );
        edtDrugGiven6 = (EditText)view.findViewById( R.id.edtDrugGiven6 );



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

            drugspn1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr1 = drugs[i];
                }
            });
            drugspn2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr2 = drugs[i];
                }
            });
            drugspn3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr3 = drugs[i];
                }
            });
            drugspn4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr4 = drugs[i];
                }
            });
            drugspn5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr5 = drugs[i];
                }
            });
            drugspn6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    drugStr6 = drugs[i];
                }
            });


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

    public void createJson(){
        String time1 = edtDrugTime1.getText().toString();
        String dosage1 = edtDrugDose1.getText().toString();
        String route1 = edtDrugRoute1.getText().toString();
        String givenBy1 = edtDrugGiven1.getText().toString();

        try{
            drugsAdministered.put("Time1",time1);
            drugsAdministered.put("Drug Name1",drugStr1);
            drugsAdministered.put("Dosage1",dosage1);
            drugsAdministered.put("Route1",route1);
            drugsAdministered.put("Given By1",givenBy1);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String time2 = edtDrugTime2.getText().toString();
        String dosage2 = edtDrugDose2.getText().toString();
        String route2 = edtDrugRoute2.getText().toString();
        String givenBy2 = edtDrugGiven2.getText().toString();

        try{
            drugsAdministered.put("Time2",time2);
            drugsAdministered.put("Drug Name2",drugStr2);
            drugsAdministered.put("Dosage2",dosage2);
            drugsAdministered.put("Route2",route2);
            drugsAdministered.put("Given By2",givenBy2);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String time3 = edtDrugTime3.getText().toString();
        String dosage3 = edtDrugDose3.getText().toString();
        String route3 = edtDrugRoute3.getText().toString();
        String givenBy3 = edtDrugGiven3.getText().toString();

        try{
            drugsAdministered.put("Time3",time3);
            drugsAdministered.put("Drug Name3",drugStr3);
            drugsAdministered.put("Dosage3",dosage3);
            drugsAdministered.put("Route3",route3);
            drugsAdministered.put("Given By3",givenBy3);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String time4 = edtDrugTime4.getText().toString();
        String dosage4 = edtDrugDose4.getText().toString();
        String route4 = edtDrugRoute4.getText().toString();
        String givenBy4 = edtDrugGiven4.getText().toString();

        try{
            drugsAdministered.put("Time4",time4);
            drugsAdministered.put("Drug Name4",drugStr4);
            drugsAdministered.put("Dosage4",dosage4);
            drugsAdministered.put("Route4",route4);
            drugsAdministered.put("Given By4",givenBy4);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String time5 = edtDrugTime5.getText().toString();
        String dosage5 = edtDrugDose5.getText().toString();
        String route5 = edtDrugRoute5.getText().toString();
        String givenBy5 = edtDrugGiven5.getText().toString();

        try{
            drugsAdministered.put("Time5",time5);
            drugsAdministered.put("Drug Name5",drugStr5);
            drugsAdministered.put("Dosage5",dosage5);
            drugsAdministered.put("Route5",route5);
            drugsAdministered.put("Given By5",givenBy5);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String time6 = edtDrugTime6.getText().toString();
        String dosage6 = edtDrugDose6.getText().toString();
        String route6 = edtDrugRoute6.getText().toString();
        String givenBy6 = edtDrugGiven6.getText().toString();

        try{
            drugsAdministered.put("Time6",time6);
            drugsAdministered.put("Drug Name6",drugStr6);
            drugsAdministered.put("Dosage6",dosage6);
            drugsAdministered.put("Route6",route6);
            drugsAdministered.put("Given By6",givenBy6);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
