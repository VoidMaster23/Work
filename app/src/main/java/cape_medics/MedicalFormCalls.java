package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.Cache;
import com.example.cape_medics.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MedicalFormCalls extends Fragment {
    private ImageView imageView9;
    private TextView textView5;
    private TextView KMs;
    private TextView received;
    private EditText callreceivedTime;
    private EditText callreceived;
    private TextView dispached;
    private EditText calldispachedTime;
    private EditText calldispached;
    private TextView enRoute;
    private EditText callEnRouteTime;
    private EditText callEnRoute;
    private TextView arrived;
    private EditText callArrivedTime;
    private EditText callArrived;
    private TextView depart;
    private EditText calldepartTime;
    private EditText calldepart;
    private TextView hospitalArrive;
    private EditText callHospitalArriveTime;
    private EditText callHospitalArrive;
    private TextView handover;
    private EditText callHandoverTime;
    private EditText callHandover;
    private TextView textView6;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;
    private CheckBox checkBox13;
    private CheckBox checkBox3;
    List<CheckBox> checkBoxList;
    Cache cache;
    String saved;
    JSONObject load;

    JSONObject callDetails;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-07-19 20:29:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

    }


    public MedicalFormCalls(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_medical_form_calls,container,false);
        imageView9 = view.findViewById( R.id.imageView12 );
        textView5 = view.findViewById( R.id.textView5 );
        KMs = view.findViewById( R.id.KMs );
        received = view.findViewById( R.id.received );
        callreceivedTime = view.findViewById( R.id.callreceivedTime );
        callreceived = view.findViewById( R.id.callreceived );
        dispached = view.findViewById( R.id.dispached );
        calldispachedTime = view.findViewById( R.id.calldispachedTime );
        calldispached = view.findViewById( R.id.calldispached );
        enRoute = view.findViewById( R.id.enRoute );
        callEnRouteTime = view.findViewById( R.id.callEnRouteTime );
        callEnRoute = view.findViewById( R.id.callEnRoute );
        arrived = view.findViewById( R.id.arrived );
        callArrivedTime = view.findViewById( R.id.callArrivedTime );
        callArrived = view.findViewById( R.id.callArrived );
        depart = view.findViewById( R.id.depart );
        calldepartTime = view.findViewById( R.id.calldepartTime );
        calldepart = view.findViewById( R.id.calldepart );
        hospitalArrive = view.findViewById( R.id.hospitalArrive );
        callHospitalArriveTime = view.findViewById( R.id.callHospitalArriveTime );
        callHospitalArrive = view.findViewById( R.id.callHospitalArrive );
        handover = view.findViewById( R.id.handover );
        callHandoverTime = view.findViewById( R.id.callHandoverTime );
        callHandover = view.findViewById( R.id.callHandover );
        textView6 = view.findViewById( R.id.textView6 );
        checkBox8 = view.findViewById( R.id.checkBox8 );
        checkBox9 = view.findViewById( R.id.checkBox9 );
        checkBox10 = view.findViewById( R.id.checkBox10 );
        checkBox11 = view.findViewById( R.id.checkBox11 );
        checkBox12 = view.findViewById( R.id.checkBox12 );
        //checkBox13 = view.findViewById( R.id.checkBox13 );
        checkBox3 = view.findViewById( R.id.notApplicableCheckBox );
        callDetails = new JSONObject();

        checkBoxList = Arrays.asList(checkBox8,checkBox9,checkBox10,checkBox11,checkBox12,checkBox13,checkBox3);
        cache = new Cache(getContext());
        saved = cache.getStringProperty("callDetails");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                callreceivedTime.setText(callDetails.getString("Call_Received_Time"));
                callreceived.setText(callDetails.getString("Call_Received_KM"));
                calldispachedTime.setText(callDetails.getString("Dispached_Time"));
                calldispached.setText(callDetails.getString("Dispached_KM"));
                callEnRouteTime.setText(callDetails.getString("En-Route_Time"));
                callEnRoute.setText(callDetails.getString("En-Route_KM"));
                callArrivedTime.setText(callDetails.getString("Arrived_Time"));
                calldepartTime.setText(callDetails.getString("Arrived_KM"));
                calldepart.setText(callDetails.getString("Depart_Time"));
                callHospitalArriveTime.setText(callDetails.getString("Depart_KM"));
                callHospitalArrive.setText(callDetails.getString("Hospital_Time"));
                callHandoverTime.setText(callDetails.getString("Hospital_KM"));
                callHandover.setText(callDetails.getString("Hospital_Time"));
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

        //callRec
        String callTime = callEnRouteTime.getText().toString();
        String callKM = callreceived.getText().toString();

        //dispach
        String dispatchTime = calldispachedTime.getText().toString();
        String dispatchKM = calldispached.getText().toString();

        //enroute
        String enrouteTime = callEnRouteTime.getText().toString();
        String enrouteKM = callEnRoute.getText().toString();

        //arrived
        String arriveTime = callArrivedTime.getText().toString();
        String arriveKM = callArrived.getText().toString();

        //Depart
        String departTime = calldepartTime.getText().toString();
        String departKM = calldepart.getText().toString();

        //arriveHosp
        String hospitalTime = callHospitalArriveTime.getText().toString();
        String hospitalKM = callHospitalArrive.getText().toString();

        //handover
        String handoverTime = callHandoverTime.getText().toString();
        String handoverKM = callHandover.getText().toString();

        //call priority
        String priority;



        if(checkBox8.isChecked()) {priority = checkBox8.getText().toString();}
        else if(checkBox9.isChecked()){
            priority = checkBox9.getText().toString();
        } else if(checkBox10.isChecked()){
            priority = checkBox10.getText().toString();
        } else if(checkBox11.isChecked()){
            priority = checkBox11.getText().toString();
        } else if(checkBox12.isChecked()){
            priority = checkBox12.getText().toString();
        } else{
            priority = checkBox13.getText().toString();
        }

        callDetails = new JSONObject();

        try{
            callDetails.put("Call_Received_Time",callTime);
            callDetails.put("Call_Received_KM",callKM);
            callDetails.put("Dispached_Time",dispatchTime);
            callDetails.put("Dispached_KM",dispatchKM);
            callDetails.put("En-Route_Time",enrouteTime);
            callDetails.put("En-Route_KM",enrouteKM);
            callDetails.put("Arrived_Time",arriveTime);
            callDetails.put("Arrived_KM", arriveKM);
            callDetails.put("Depart_Time",departTime);
            callDetails.put("Depart_KM",departKM);
            callDetails.put("Hospital_Time",hospitalTime);
            callDetails.put("Hospital_KM",hospitalKM);
            callDetails.put("Handover_Time",handoverTime);
            callDetails.put("Handover_KM",handoverKM);
            callDetails.put("Call_Priority",priority);



        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("callDetails",callDetails.toString());
        return callDetails;
    }


}
