package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONObject;

import java.util.Calendar;


public class Cannulation extends Fragment {
    private ExpandableRelativeLayout expLay1, expLay2;
    private Button button1, button2;
    private Spinner spnSize1, spnSize2, spn1,spn2;


    private CheckBox chkNA;
    private ScrollView canscrl1;

    private TextView lblTim1;
    private TextView edtTim1;
    private TextView lblSize1;
    private EditText edtSize1;
    private TextView lblAtt1;
    private EditText edtAtt1;
    private TextView lblSite1;
    private EditText edtSite1;
    private TextView lblRate1;
    private EditText edtRate1;
    private TextView lblVol1;
    private EditText edtVol1;
    private TextView lblFluid1;


    private ScrollView canscrl2;

    private TextView lblTim2;
    private TextView edtTim2;
    private TextView lblSize2;
    private EditText edtSize2;
    private TextView lblAtt2;
    private EditText edtAtt2;
    private TextView lblSite2;
    private EditText edtSite2;
    private TextView lblRate2;
    private EditText edtRate2;
    private TextView lblVol2;
    private EditText edtVol2;
    private TextView lblFluid2;
    private Button btnAtt1, btnAtt2, btnAtt3, btnAtt4;
    String fluidType1;
    String fluidType2;
    String size1, size2;

    JSONObject cannulation;

    private String[] fluid = {"Saline 0.9%","Ringers Lactate","Colloids","Plasma","Whole Blood"};

    private String[] size = {"14","16","18","20","22","24"};


    String time1, site1, rate1,volume1;

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
        btnAtt1 = view.findViewById(R.id.btnAtt1);
        btnAtt2 = view.findViewById(R.id.btnAtt2);
        btnAtt3 = view.findViewById(R.id.btnAtt3);
        btnAtt4 = view.findViewById(R.id.btnAtt4);

        //make scroll to the next one on check
        chkNA = view.findViewById(R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(view1 -> {
            medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);
        });

        Init();

        spnSize1 = view.findViewById(R.id.sizeSpin1);
       // spnSize2 = view.findViewById(R.id.sizeS)
        spn1 = view.findViewById(R.id.canSpin1);
        spn2 = view.findViewById(R.id.canSpin2);

        ArrayAdapter a1 = new ArrayAdapter(getContext(), R.layout.custom_spinner,fluid);
        ArrayAdapter a2 = new ArrayAdapter(getContext(), R.layout.custom_spinner,fluid);
        ArrayAdapter a = new ArrayAdapter(getContext(), R.layout.custom_spinner,size);

        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSize1.setAdapter(a);
        spn1.setAdapter(a1);
        spn2.setAdapter(a2);

        spnSize1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size1 = size[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fluidType1 = fluid[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fluidType2 = fluid[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chkNA = view.findViewById( R.id.chkNA );

        canscrl1 = view.findViewById( R.id.canscrl1 );

        lblTim1 = view.findViewById( R.id.lblTim1 );
        edtTim1 = view.findViewById( R.id.edtTim1 );
        lblSize1 = view.findViewById( R.id.lblSize1 );
        lblAtt1 = view.findViewById( R.id.lblAtt1 );
        //edtAtt1 = view.findViewById( R.id.edtAtt1 );
        lblSite1 = view.findViewById( R.id.lblSite1 );
        edtSite1 = view.findViewById( R.id.edtSite1 );
        lblRate1 = view.findViewById( R.id.lblRate1 );
        edtRate1 = view.findViewById( R.id.edtRate1 );
        lblVol1 = view.findViewById( R.id.lblVol1 );
        edtVol1 = view.findViewById( R.id.edtVol1 );
        lblFluid1 = view.findViewById( R.id.lblFluid1 );
        canscrl2 = view.findViewById( R.id.canscrl2 );
        lblTim2 = view.findViewById( R.id.lblTim2 );
        edtTim2 = view.findViewById( R.id.edtTim2 );
        lblSize2 = view.findViewById( R.id.lblSize2 );
        edtSize2 = view.findViewById( R.id.edtSize2 );
        lblAtt2 = view.findViewById( R.id.lblAtt2 );
        edtAtt2 = view.findViewById( R.id.edtAtt2 );
        lblSite2 = view.findViewById( R.id.lblSite2 );
        edtSite2 = view.findViewById( R.id.edtSite2 );
        lblRate2 = view.findViewById( R.id.lblRate2 );
        edtRate2 = view.findViewById( R.id.edtRate2 );
        lblVol2 = view.findViewById( R.id.lblVol2 );
        edtVol2 = view.findViewById( R.id.edtVol2 );
        lblFluid2 = view.findViewById( R.id.lblFluid2 );

        TimePicker();

        return view;
    }

    Context mContext;
    private void TimePicker() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mContext = getActivity();

        edtTim1.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtTim1.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        edtTim2.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> edtTim2.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });
    }


    private void Init(){

        button1.setOnClickListener(view1 -> expLay1.toggle());
        button2.setOnClickListener(view12 -> expLay2.toggle());

        btnAtt1.setOnClickListener(v -> {
            btnAtt1.setBackgroundResource(R.drawable.orangeshape);
            btnAtt2.setBackgroundResource(R.drawable.whiteshape);
            btnAtt3.setBackgroundResource(R.drawable.whiteshape);
            btnAtt4.setBackgroundResource(R.drawable.whiteshape);

            attempts1 = 1;
        });

        btnAtt2.setOnClickListener(v -> {
            btnAtt1.setBackgroundResource(R.drawable.whiteshape);
            btnAtt2.setBackgroundResource(R.drawable.orangeshape);
            btnAtt3.setBackgroundResource(R.drawable.whiteshape);
            btnAtt4.setBackgroundResource(R.drawable.whiteshape);

            attempts1 = 2;
        });

        btnAtt3.setOnClickListener(v -> {
            btnAtt1.setBackgroundResource(R.drawable.whiteshape);
            btnAtt2.setBackgroundResource(R.drawable.whiteshape);
            btnAtt3.setBackgroundResource(R.drawable.orangeshape);
            btnAtt4.setBackgroundResource(R.drawable.whiteshape);

            attempts1 = 3;
        });

        btnAtt4.setOnClickListener(v -> {
            btnAtt1.setBackgroundResource(R.drawable.whiteshape);
            btnAtt2.setBackgroundResource(R.drawable.whiteshape);
            btnAtt3.setBackgroundResource(R.drawable.whiteshape);
            btnAtt4.setBackgroundResource(R.drawable.orangeshape);

            attempts1 = 4;
        });
    }

    int attempts1 = 1;

    public JSONObject createJson(){

        cannulation = new JSONObject();



        time1 = edtTim1.getText().toString();
        size1 = edtSize1.getText().toString();
         site1 = edtSite1.getText().toString();
        rate1 = edtRate1.getText().toString();
        volume1 = edtVol1.getText().toString();



        try{
            if(!chkNA.isChecked()) {
                cannulation.put("Time1", time1);
                cannulation.put("Size1", size1);
                cannulation.put("Attempt1", attempts1);
                cannulation.put("Site1", site1);
                cannulation.put("Rate1", rate1);
                cannulation.put("Volume1", volume1);
                cannulation.put("Fluid type1", fluidType1);
            }else if(chkNA.isChecked()){
                cannulation
                        .put("Status","Not applicable");
            }

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //make these global like the ones above
//        String time2 = edtTim2.getText().toString();
//        String size2 = edtSize2.getText().toString();
//        String attempt2 = edtAtt2.getText().toString();
//        String site2 = edtSite2.getText().toString();
//        String rate2 = edtRate2.getText().toString();
//        String volume2 = edtVol2.getText().toString();
//
//
//       this should be like what I did before
//        try{
//
//            cannulation.put("Time2",time2);
//            cannulation.put("Size2",size2);
//            cannulation.put("Attempt2",attempt2);
//            cannulation.put("Site2",site2);
//            cannulation.put("Rate2",rate2);
//            cannulation.put("Volume2",volume2);
//
//
//        }catch (Exception e){
//            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//        }

        return cannulation;
    }


    // Need to speak to vally about what is required for this
  /*  public boolean validate(){
        boolean valid = true;
        Looper.prepare();

        if(time1.isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Cannulation: Please fill in all the details",Toast.LENGTH_SHORT).show();
        }

        if(valid){
            if(site1.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Cannulation: please fill in all the details", Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(rate1.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Cannulation: please fill in all the details", Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(volume1.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Cannulation: please fill in all the details", Toast.LENGTH_SHORT).show();
            }
        }

        Looper.loop();
        return valid;
    }*/


}