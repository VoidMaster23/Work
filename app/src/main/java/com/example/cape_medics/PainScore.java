package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class PainScore extends Fragment {

    EditText pre, post1, post2, hosp;

    private Spinner spnPre, spnPost1,spnPost2,spnHosp;
    Button show;

    TextView textView1, textView2, textView3, textView4;;
    String preStr, post5Str, post10Str, hospStr;
    JSONObject painScore;

    String sizeType;

    private String[] size = {"1","2","3","4","5","6","7","8","9","10"};
    
    public CheckBox chkNA;

    public PainScore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_score, container, false);

//        imageView = view.findViewById(R.id.imgPain);



        spnPre = view.findViewById(R.id.editPre);
        spnPost1 = view.findViewById(R.id.editPost5);
        spnPost2 = view.findViewById(R.id.editPost10);
        spnHosp = view.findViewById(R.id.editAtHosp);

        ArrayAdapter a = new ArrayAdapter(getContext(), R.layout.custom_spinner,size);
        ArrayAdapter a1 = new ArrayAdapter(getContext(), R.layout.custom_spinner,size);
        ArrayAdapter a2 = new ArrayAdapter(getContext(), R.layout.custom_spinner,size);
        ArrayAdapter a3 = new ArrayAdapter(getContext(), R.layout.custom_spinner,size);

        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spnPre.setAdapter(a);
        spnPost1.setAdapter(a1);
        spnPost2.setAdapter(a2);
        spnHosp.setAdapter(a3);

        spnPre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sizeType = size[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnPost1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sizeType = size[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnPost2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sizeType = size[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnHosp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sizeType = size[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textView1 = view.findViewById(R.id.textView51);
        textView2 = view.findViewById(R.id.textView52);
        textView3 = view.findViewById(R.id.textView53);
        textView4 = view.findViewById(R.id.textView54);
        chkNA = view.findViewById( R.id.notApplicableCheckBox );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        //show = view.findViewById(R.id.btnPainScale);
        //painLay = view.findViewById(R.id.relLayPain);

        /* show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getVisibility() == View.INVISIBLE){
                    pre.setVisibility(View.INVISIBLE);
                    post1.setVisibility(View.INVISIBLE);
                    post2.setVisibility(View.INVISIBLE);
                    hosp.setVisibility(View.INVISIBLE);

                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textView4.setVisibility(View.INVISIBLE);

                    show.setVisibility(View.INVISIBLE);

                    painLay.setBackgroundColor(getResources().getColor(android.R.color.black));
                    imageView.setVisibility(View.VISIBLE);




                }
            }
        });*/



        return view;
    }

    public JSONObject createJson(){

        painScore = new JSONObject();

        preStr = spnPre.getSelectedItem().toString();
        post5Str = spnPost1.getSelectedItem().toString();
        post10Str = spnPost2.getSelectedItem().toString();
        hospStr = spnHosp.getSelectedItem().toString();

        try{

            if(!chkNA.isChecked() && validate()) {
                painScore.put("Pre-Analgesia", preStr);
                painScore.put("Post 5", post5Str);
                painScore.put("Post 10", post10Str);
                painScore.put("Hospital", hospStr);
            }else if(chkNA.isChecked()){
                painScore.put("Status","Not applicable");
            }


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return painScore;
    }
    
    public boolean validate() {
        boolean valid = true;
        Looper.prepare();



        if (preStr.isEmpty() || post5Str.isEmpty() || post10Str.isEmpty() || hospStr.isEmpty()) {
            valid = false;
            Toast.makeText(getContext(), "Pain Score: Please record enter all the details", Toast.LENGTH_SHORT).show();
        }

        Looper.loop();
        return valid;
    }

}
