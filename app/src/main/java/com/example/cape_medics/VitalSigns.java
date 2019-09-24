package com.example.cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class VitalSigns extends Fragment {

 ExpandableRelativeLayout expandableRelativeLayout1, expandableRelativeLayout2,expandableRelativeLayout3, expandableRelativeLayout4;
Button button1,button2,button3, button4;
    private ImageView imageView15;
    private CheckBox chkNA;
    private Button expBut1;
    private ScrollView scrl1;
    private ExpandableRelativeLayout expLay1;
    private TextView lblTime1;
    private EditText edtTime1;
    private TextView lblPulse1;
    private EditText edtPulse1;
    private TextView lblBp1;
    private EditText edtBp1;
    private TextView lblSpo1;
    private EditText edtSpo1;
    private TextView lblResp1;
    private EditText edtResp1;
    private TextView lblHgt1;
    private EditText edtHgt1;
    private TextView lblCO21;
    private EditText edtCO21;
    private TextView lblFlow1;
    private EditText edtFlow1;
    private TextView lblPearl1;
    private CheckBox chkLeft1;
    private CheckBox chkRight1;
    private TextView lblPupSize1;
    private TextView lblPupLeft1;
    private TextView lblPupRight1;
    private Spinner spnLeftSize1;
    private Spinner spnRightSize1;
    private Button expBut2;
    private ScrollView scrl2;
    private ExpandableRelativeLayout expLay2;
    private TextView lblTime2;
    private EditText edtTime2;
    private TextView lblPulse2;
    private EditText edtPulse2;
    private TextView lblBp2;
    private EditText edtBp2;
    private TextView lblSpo2;
    private EditText edtSpo2;
    private TextView lblResp2;
    private EditText edtResp2;
    private TextView lblHgt2;
    private EditText edtHgt2;
    private TextView lblCO22;
    private EditText edtCO22;
    private TextView lblFlow2;
    private EditText edtFlow2;
    private TextView lblPearl2;
    private CheckBox chkLeft2;
    private CheckBox chkRight2;
    private TextView lblPupSize2;
    private TextView lblPupLeft2;
    private TextView lblPupRight2;
    private Spinner spnLeftSize2;
    private Spinner spnRightSize2;
    private Button expBut3;
    private ScrollView scrl3;
    private ExpandableRelativeLayout expLay3;
    private TextView lblTime3;
    private EditText edtTime3;
    private TextView lblPulse3;
    private EditText edtPulse3;
    private TextView lblBp3;
    private EditText edtBp3;
    private TextView lblSpo3;
    private EditText edtSpo3;
    private TextView lblResp3;
    private EditText edtResp3;
    private TextView lblHgt3;
    private EditText edtHgt3;
    private TextView lblCO23;
    private EditText edtCO23;
    private TextView lblFlow3;
    private EditText edtFlow3;
    private TextView lblPearl3;
    private CheckBox chkLeft3;
    private CheckBox chkRight3;
    private TextView lblPupSize3;
    private TextView lblPupLeft3;
    private TextView lblPupRight3;
    private Spinner spnLeftSize3;
    private Spinner spnRightSize3;
    private Button expBut4;
    private ExpandableRelativeLayout expLay4;
    private TextView lblTime4;
    private EditText edtTime4;
    private TextView lblPulse4;
    private EditText edtPulse4;
    private TextView lblBp4;
    private EditText edtBp4;
    private TextView lblSpo4;
    private EditText edtSpo4;
    private TextView lblResp4;
    private EditText edtResp4;
    private TextView lblHgt4;
    private EditText edtHgt4;
    private TextView lblCO24;
    private EditText edtCO24;
    private TextView lblFlow4;
    private EditText edtFlow4;
    private TextView lblPearl4;
    private CheckBox chkLeft4;
    private CheckBox chkRight4;
    private TextView lblPupSize4;
    private TextView lblPupLeft4;
    private TextView lblPupRight4;
    private Spinner spnLeftSize4;
    private Spinner spnRightSize4;

    JSONObject vitalSigns;

    List<CheckBox> checkBoxList;
    Cache cache;
    String saved;
    JSONObject load;


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

        imageView15 = (ImageView)view.findViewById( R.id.imageView15 );
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        expBut1 = (Button)view.findViewById( R.id.expBut1 );
        scrl1 = (ScrollView)view.findViewById( R.id.scrl1 );
        expLay1 = (ExpandableRelativeLayout)view.findViewById( R.id.expLay1 );
        lblTime1 = (TextView)view.findViewById( R.id.lblTime1 );
        edtTime1 = (EditText)view.findViewById( R.id.edtTime1 );
        lblPulse1 = (TextView)view.findViewById( R.id.lblPulse1 );
        edtPulse1 = (EditText)view.findViewById( R.id.edtPulse1 );
        lblBp1 = (TextView)view.findViewById( R.id.lblBp1 );
        edtBp1 = (EditText)view.findViewById( R.id.edtBp1 );
        lblSpo1 = (TextView)view.findViewById( R.id.lblSpo1 );
        edtSpo1 = (EditText)view.findViewById( R.id.edtSpo1 );
        lblResp1 = (TextView)view.findViewById( R.id.lblResp1 );
        edtResp1 = (EditText)view.findViewById( R.id.edtResp1 );
        lblHgt1 = (TextView)view.findViewById( R.id.lblHgt1 );
        edtHgt1 = (EditText)view.findViewById( R.id.edtHgt1 );
        lblCO21 = (TextView)view.findViewById( R.id.lblCO21 );
        edtCO21 = (EditText)view.findViewById( R.id.edtCO21 );
        lblFlow1 = (TextView)view.findViewById( R.id.lblFlow1 );
        edtFlow1 = (EditText)view.findViewById( R.id.edtFlow1 );
        lblPearl1 = (TextView)view.findViewById( R.id.lblPearl1 );
        chkLeft1 = (CheckBox)view.findViewById( R.id.chkLeft1 );
        chkRight1 = (CheckBox)view.findViewById( R.id.chkRight1 );
        lblPupSize1 = (TextView)view.findViewById( R.id.lblPupSize1 );
        lblPupLeft1 = (TextView)view.findViewById( R.id.lblPupLeft1 );
        lblPupRight1 = (TextView)view.findViewById( R.id.lblPupRight1 );
        spnLeftSize1 = (Spinner)view.findViewById( R.id.spnLeftSize1 );
        spnRightSize1 = (Spinner)view.findViewById( R.id.spnRightSize1 );
        expBut2 = (Button)view.findViewById( R.id.expBut2 );
        scrl2 = (ScrollView)view.findViewById( R.id.scrl2 );
        expLay2 = (ExpandableRelativeLayout)view.findViewById( R.id.expLay2 );
        lblTime2 = (TextView)view.findViewById( R.id.lblTime2 );
        edtTime2 = (EditText)view.findViewById( R.id.edtTime2 );
        lblPulse2 = (TextView)view.findViewById( R.id.lblPulse2 );
        edtPulse2 = (EditText)view.findViewById( R.id.edtPulse2 );
        lblBp2 = (TextView)view.findViewById( R.id.lblBp2 );
        edtBp2 = (EditText)view.findViewById( R.id.edtBp2 );
        lblSpo2 = (TextView)view.findViewById( R.id.lblSpo2 );
        edtSpo2 = (EditText)view.findViewById( R.id.edtSpo2 );
        lblResp2 = (TextView)view.findViewById( R.id.lblResp2 );
        edtResp2 = (EditText)view.findViewById( R.id.edtResp2 );
        lblHgt2 = (TextView)view.findViewById( R.id.lblHgt2 );
        edtHgt2 = (EditText)view.findViewById( R.id.edtHgt2 );
        lblCO22 = (TextView)view.findViewById( R.id.lblCO22 );
        edtCO22 = (EditText)view.findViewById( R.id.edtCO22 );
        lblFlow2 = (TextView)view.findViewById( R.id.lblFlow2 );
        edtFlow2 = (EditText)view.findViewById( R.id.edtFlow2 );
        lblPearl2 = (TextView)view.findViewById( R.id.lblPearl2 );
        chkLeft2 = (CheckBox)view.findViewById( R.id.chkLeft2 );
        chkRight2 = (CheckBox)view.findViewById( R.id.chkRight2 );
        lblPupSize2 = (TextView)view.findViewById( R.id.lblPupSize2 );
        lblPupLeft2 = (TextView)view.findViewById( R.id.lblPupLeft2 );
        lblPupRight2 = (TextView)view.findViewById( R.id.lblPupRight2 );
        spnLeftSize2 = (Spinner)view.findViewById( R.id.spnLeftSize2 );
        spnRightSize2 = (Spinner)view.findViewById( R.id.spnRightSize2 );
        expBut3 = (Button)view.findViewById( R.id.expBut3 );
        scrl3 = (ScrollView)view.findViewById( R.id.scrl3 );
        expLay3 = (ExpandableRelativeLayout)view.findViewById( R.id.expLay3 );
        lblTime3 = (TextView)view.findViewById( R.id.lblTime3 );
        edtTime3 = (EditText)view.findViewById( R.id.edtTime3 );
        lblPulse3 = (TextView)view.findViewById( R.id.lblPulse3 );
        edtPulse3 = (EditText)view.findViewById( R.id.edtPulse3 );
        lblBp3 = (TextView)view.findViewById( R.id.lblBp3 );
        edtBp3 = (EditText)view.findViewById( R.id.edtBp3 );
        lblSpo3 = (TextView)view.findViewById( R.id.lblSpo3 );
        edtSpo3 = (EditText)view.findViewById( R.id.edtSpo3 );
        lblResp3 = (TextView)view.findViewById( R.id.lblResp3 );
        edtResp3 = (EditText)view.findViewById( R.id.edtResp3 );
        lblHgt3 = (TextView)view.findViewById( R.id.lblHgt3 );
        edtHgt3 = (EditText)view.findViewById( R.id.edtHgt3 );
        lblCO23 = (TextView)view.findViewById( R.id.lblCO23 );
        edtCO23 = (EditText)view.findViewById( R.id.edtCO23 );
        lblFlow3 = (TextView)view.findViewById( R.id.lblFlow3 );
        edtFlow3 = (EditText)view.findViewById( R.id.edtFlow3 );
        lblPearl3 = (TextView)view.findViewById( R.id.lblPearl3 );
        chkLeft3 = (CheckBox)view.findViewById( R.id.chkLeft3 );
        chkRight3 = (CheckBox)view.findViewById( R.id.chkRight3 );
        lblPupSize3 = (TextView)view.findViewById( R.id.lblPupSize3 );
        lblPupLeft3 = (TextView)view.findViewById( R.id.lblPupLeft3 );
        lblPupRight3 = (TextView)view.findViewById( R.id.lblPupRight3 );
        spnLeftSize3 = (Spinner)view.findViewById( R.id.spnLeftSize3 );
        spnRightSize3 = (Spinner)view.findViewById( R.id.spnRightSize3 );
        expBut4 = (Button)view.findViewById( R.id.expBut4 );
        expLay4 = (ExpandableRelativeLayout)view.findViewById( R.id.expLay4 );
        lblTime4 = (TextView)view.findViewById( R.id.lblTime4 );
        edtTime4 = (EditText)view.findViewById( R.id.edtTime4 );
        lblPulse4 = (TextView)view.findViewById( R.id.lblPulse4 );
        edtPulse4 = (EditText)view.findViewById( R.id.edtPulse4 );
        lblBp4 = (TextView)view.findViewById( R.id.lblBp4 );
        edtBp4 = (EditText)view.findViewById( R.id.edtBp4 );
        lblSpo4 = (TextView)view.findViewById( R.id.lblSpo4 );
        edtSpo4 = (EditText)view.findViewById( R.id.edtSpo4 );
        lblResp4 = (TextView)view.findViewById( R.id.lblResp4 );
        edtResp4 = (EditText)view.findViewById( R.id.edtResp4 );
        lblHgt4 = (TextView)view.findViewById( R.id.lblHgt4 );
        edtHgt4 = (EditText)view.findViewById( R.id.edtHgt4 );
        lblCO24 = (TextView)view.findViewById( R.id.lblCO24 );
        edtCO24 = (EditText)view.findViewById( R.id.edtCO24 );
        lblFlow4 = (TextView)view.findViewById( R.id.lblFlow4 );
        edtFlow4 = (EditText)view.findViewById( R.id.edtFlow4 );
        lblPearl4 = (TextView)view.findViewById( R.id.lblPearl4 );
        chkLeft4 = (CheckBox)view.findViewById( R.id.chkLeft4 );
        chkRight4 = (CheckBox)view.findViewById( R.id.chkRight4 );
        lblPupSize4 = (TextView)view.findViewById( R.id.lblPupSize4 );
        lblPupLeft4 = (TextView)view.findViewById( R.id.lblPupLeft4 );
        lblPupRight4 = (TextView)view.findViewById( R.id.lblPupRight4 );
        spnLeftSize4 = (Spinner)view.findViewById( R.id.spnLeftSize4 );
        spnRightSize4 = (Spinner)view.findViewById( R.id.spnRightSize4 );

        checkBoxList = Arrays.asList(chkLeft1,chkLeft2,chkLeft3,chkLeft4,chkRight1,chkRight2,chkRight3,chkRight4);
        cache = new Cache(getContext());
        saved = cache.getStringProperty("vitalSigns");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                edtTime1.setText(vitalSigns.getString("Time1"));
                edtPulse1.setText(vitalSigns.getString("Pulse1"));
                edtBp1.setText(vitalSigns.getString("BP1"));
                edtSpo1.setText(vitalSigns.getString("spo21"));
                edtResp1.setText(vitalSigns.getString("resp1"));
                edtHgt1.setText(vitalSigns.getString("hgt1"));
                edtCO21.setText(vitalSigns.getString("co21"));
                edtFlow1.setText(vitalSigns.getString("peak1"));

                edtTime2.setText(vitalSigns.getString("Time2"));
                edtPulse2.setText(vitalSigns.getString("Pulse2"));
                edtBp2.setText(vitalSigns.getString("BP2"));
                edtSpo2.setText(vitalSigns.getString("spo22"));
                edtResp2.setText(vitalSigns.getString("resp2"));
                edtHgt2.setText(vitalSigns.getString("hgt2"));
                edtCO22.setText(vitalSigns.getString("co22"));
                edtFlow2.setText(vitalSigns.getString("peak2"));

                edtTime3.setText(vitalSigns.getString("Time3"));
                edtPulse3.setText(vitalSigns.getString("Pulse3"));
                edtBp3.setText(vitalSigns.getString("BP3"));
                edtSpo3.setText(vitalSigns.getString("spo23"));
                edtResp3.setText(vitalSigns.getString("resp3"));
                edtHgt3.setText(vitalSigns.getString("hgt3"));
                edtCO23.setText(vitalSigns.getString("co23"));
                edtFlow3.setText(vitalSigns.getString("peak3"));

                edtTime4.setText(vitalSigns.getString("Time4"));
                edtPulse4.setText(vitalSigns.getString("Pulse4"));
                edtBp4.setText(vitalSigns.getString("BP4"));
                edtSpo4.setText(vitalSigns.getString("spo24"));
                edtResp4.setText(vitalSigns.getString("resp4"));
                edtHgt4.setText(vitalSigns.getString("hgt4"));
                edtCO24.setText(vitalSigns.getString("co24"));
                edtFlow4.setText(vitalSigns.getString("peak4"));

                Iterator<String> keys = load.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j<checkBoxList.size();j++) {
                        if (checkBoxList.get(j) != null) {
                            if (item.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);

                            }
                        }
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

     return view;
    }


    public JSONObject createJson(){

        vitalSigns = new JSONObject();

        String time1 = edtTime1.getText().toString();
        String pulse1 = edtPulse1.getText().toString();
        String BP1 = edtBp1.getText().toString();
        String spo21 =edtSpo1.getText().toString();
        String resp1 = edtResp1.getText().toString();
        String hgt1 = edtHgt1.getText().toString();
        String co21 = edtCO21.getText().toString();
        String peak1 = edtFlow1.getText().toString();

        String checkedLeft1 = null;
        if (chkLeft1.isChecked()){
            checkedLeft1 = chkLeft1.getText().toString();
        }

        String checkedRight1 = null;
        if(chkRight1.isChecked()){
            checkedRight1 = chkRight1.getText().toString();
        }

        try{
            vitalSigns.put("Time1",time1);
            vitalSigns.put("Pulse1",pulse1);
            vitalSigns.put("BP1",BP1);
            vitalSigns.put("spo21",spo21);
            vitalSigns.put("resp1",resp1);
            vitalSigns.put("hgt1",hgt1);
            vitalSigns.put("co21",co21);
            vitalSigns.put("peak1",peak1);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String time2 = edtTime2.getText().toString();
        String pulse2 = edtPulse2.getText().toString();
        String BP2 = edtBp2.getText().toString();
        String spo22 =edtSpo2.getText().toString();
        String resp2 = edtResp2.getText().toString();
        String hgt2 = edtHgt2.getText().toString();
        String co22 = edtCO22.getText().toString();
        String peak2 = edtFlow2.getText().toString();

        String checkedLeft2 = null;
        if (chkLeft2.isChecked()){
            checkedLeft2 = chkLeft2.getText().toString();
        }

        String checkedRight2 = null;
        if(chkRight2.isChecked()){
            checkedRight2 = chkRight2.getText().toString();
        }

        try{
            vitalSigns.put("Time2",time2);
            vitalSigns.put("Pulse2",pulse2);
            vitalSigns.put("BP2",BP2);
            vitalSigns.put("spo22",spo22);
            vitalSigns.put("resp2",resp2);
            vitalSigns.put("hgt2",hgt2);
            vitalSigns.put("co22",co22);
            vitalSigns.put("peak2",peak2);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String time3 = edtTime3.getText().toString();
        String pulse3 = edtPulse3.getText().toString();
        String BP3 = edtBp3.getText().toString();
        String spo23 =edtSpo3.getText().toString();
        String resp3 = edtResp3.getText().toString();
        String hgt3= edtHgt3.getText().toString();
        String co23 = edtCO23.getText().toString();
        String peak3 = edtFlow3.getText().toString();

        String checkedLeft3 = null;
        if (chkLeft3.isChecked()){
            checkedLeft3 = chkLeft3.getText().toString();
        }

        String checkedRight3 = null;
        if(chkRight1.isChecked()){
            checkedRight3 = chkRight3.getText().toString();
        }

        try{
            vitalSigns.put("Time3",time3);
            vitalSigns.put("Pulse3",pulse3);
            vitalSigns.put("BP3",BP3);
            vitalSigns.put("spo23",spo23);
            vitalSigns.put("resp3",resp3);
            vitalSigns.put("hgt3",hgt3);
            vitalSigns.put("co23",co23);
            vitalSigns.put("peak3",peak3);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String time4= edtTime4.getText().toString();
        String pulse4 = edtPulse4.getText().toString();
        String BP4 = edtBp4.getText().toString();
        String spo24 =edtSpo4.getText().toString();
        String resp4 = edtResp4.getText().toString();
        String hgt4 = edtHgt4.getText().toString();
        String co24 = edtCO24.getText().toString();
        String peak4 = edtFlow4.getText().toString();

        String checkedLeft4 = null;
        if (chkLeft4.isChecked()){
            checkedLeft4 = chkLeft4.getText().toString();
        }

        String checkedRight4 = null;
        if(chkRight4.isChecked()){
            checkedRight4 = chkRight4.getText().toString();
        }

        try{
            vitalSigns.put("Time4",time4);
            vitalSigns.put("Pulse4",pulse4);
            vitalSigns.put("BP4",BP4);
            vitalSigns.put("spo24",spo24);
            vitalSigns.put("resp4",resp4);
            vitalSigns.put("hgt4",hgt4);
            vitalSigns.put("co24",co24);
            vitalSigns.put("peak4",peak4);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("vitalSigns",vitalSigns.toString());
        return vitalSigns;
    }



}
