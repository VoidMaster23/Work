package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.R;

import org.json.JSONObject;


public class ComaScore extends Fragment {

    String[] eyeResp = {"No Eye Opening","Open To Pain","Open To Verbal Command","Open Spontaneously"};
    String[] verbResp = {"No Verbal Response","Incomprehensible Sounds","Inappropriate Words","Confused","Orientated"};
    String[] motorResp = {"No Motor Response","Extension To Pain","Flexion To Pain","Withdraws From Pain","Localises Pain","Obeys Commands"};
    String[] spngcsResp = {"3","4-5","6-8","9-12","13-15"};
    String[] spnsbpResp = {"0","1-49","50-75","76-89",">89"};
    String[] spnrrResp = {"0","1-5","6-9",">29","10-29"};
    String[] spnrtsValueResp = {"0","1","2","3","4"};

    TextView gcs, trauma;

    Spinner eye, verb, motor, spngcs, spnsbp, spnrr, spnrtsValue;

    ArrayAdapter eyeAdapter, verbAdapter, motorAdapter, spngcsAdapter, spnsbpAdapter, spnrrAdapter, spnrtsValueAdapter;

    String gScore, tScore;

    JSONObject comaScore;

    String eyeStr, verbStr, motorStr, spngcsStr, spnsbpStr, spnrrStr, spnrtsValueStr;

    public ComaScore() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coma_score, container, false);

        gcs = view.findViewById(R.id.lblGCS);
        trauma  = view.findViewById(R.id.lblTrauma);

        eye = view.findViewById(R.id.spnEyeResponse);
        verb = view.findViewById(R.id.spnVerbalResponse);
        motor = view.findViewById(R.id.spnMotorResponse);
        spngcs = view.findViewById(R.id.spngcs);
        spnsbp = view.findViewById(R.id.spnsbp);
        spnrr = view.findViewById(R.id.spnrr);
        spnrtsValue = view.findViewById(R.id.spnrtsValue);

        eyeAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,eyeResp);
        verbAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,verbResp);
        motorAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,motorResp);
        spngcsAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,spngcsResp);
        spnsbpAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,spnsbpResp);
        spnrrAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,spnrrResp);
        spnrtsValueAdapter = new ArrayAdapter(getContext(), R.layout.custom_spinner,spnrtsValueResp);

        eyeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        verbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spngcsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnsbpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrtsValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        eye.setAdapter(eyeAdapter);
        verb.setAdapter(verbAdapter);
        motor.setAdapter(motorAdapter);
        spngcs.setAdapter(spngcsAdapter);
        spnsbp.setAdapter(spnsbpAdapter);
        spnrr.setAdapter(spnrrAdapter);
        spnrtsValue.setAdapter(spnrtsValueAdapter);

        gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
        gcs.setText(gScore);

        tScore = "Revised Trauma Score: "+traumaScore()+"/12";
        trauma.setText(tScore);

        eye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
               gcs.setText(gScore);

               tScore = "Revised Trauma Score: "+traumaScore()+"/12";
               trauma.setText(tScore);

               eyeStr = eyeResp[i];
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        motor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                motorStr = motorResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        verb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                verbStr = verbResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spngcs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                eyeStr = eyeResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnsbp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                eyeStr = eyeResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnrr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                gcs.setText(gScore);

                tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                trauma.setText(tScore);

                eyeStr = eyeResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnrtsValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //gScore = "Glasgow Coma Score: "+gcsScore()+"/15";
                //gcs.setText(gScore);

                //tScore = "Revised Trauma Score: "+traumaScore()+"/12";
                //trauma.setText(tScore);

                //eyeStr = eyeResp[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public int gcsScore (){
        int eyeScore = eye.getSelectedItemPosition()+1;
        int verbScore = verb.getSelectedItemPosition()+1;
        int motorScore = motor.getSelectedItemPosition()+1;

        return eyeScore+verbScore+motorScore;

    }

    public int traumaScore(){
        if(gcsScore() >= 13){
            return 4;
        }else if(gcsScore() >= 9){
            return 3;
        } else if( gcsScore() >= 6 ){
            return 2;
        }else if(gcsScore() >= 4){
            return 1;
        }else{
            return 0;
        }

    }

    public JSONObject  createJson(){
        comaScore = new JSONObject();
        try{
            comaScore.put("Eye Response",eyeStr);
            comaScore.put("Verbal Response",verbStr);
            comaScore.put("Motor Response",motorStr);

            comaScore.put("GCS",gcs.getText().toString());
            comaScore.put("RTS",trauma.getText().toString());
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return comaScore;
    }


}
