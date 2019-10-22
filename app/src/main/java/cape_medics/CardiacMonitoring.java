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

import com.example.cape_medics.R;

import org.json.JSONObject;


public class CardiacMonitoring extends Fragment {
    private ImageView imageView18;
    private CheckBox chkNA;
    private TextView textView13;
    private CheckBox chk3Lead;
    private CheckBox chk5Lead;
    private CheckBox chk12Lead;
    private CheckBox chkRhythm;
    private CheckBox chkCardio;
    private CheckBox chkPacing;
    private EditText editRhythm, editCardio, editPacing;
    JSONObject cardiacMonitoring;



    public CardiacMonitoring() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cardiac_monitoring, container, false);
        imageView18 = view.findViewById( R.id.imageView18 );
        chkNA = view.findViewById( R.id.chkNA );
        textView13 = view.findViewById( R.id.textView13 );
        chk3Lead = view.findViewById( R.id.chk3Lead );
        chk5Lead = view.findViewById( R.id.chk5Lead );
        chk12Lead = view.findViewById( R.id.chk12Lead );
        chkRhythm = view.findViewById( R.id.chkRhythm );
        chkCardio = view.findViewById( R.id.chkCardio );
        chkPacing = view.findViewById( R.id.chkPacing );
        editRhythm = view.findViewById( R.id.editRhythm );
        editCardio = view.findViewById( R.id.editCardio );
        editPacing = view.findViewById( R.id.editPacing );

        editRhythm.setVisibility(View.GONE);
        editCardio.setVisibility(View.GONE);
        editPacing.setVisibility(View.GONE);
        return view;
    }

    public JSONObject createJson(){
        String monitor  = null;
        cardiacMonitoring = new JSONObject();
        if(chk3Lead.isChecked()){
            monitor = chk3Lead.getText().toString();
        }else if(chk5Lead.isChecked()){
            monitor = chk5Lead.getText().toString();
        }else if(chk12Lead.isChecked()){
            monitor = chk12Lead.getText().toString();
        }
        else if(chkRhythm.isChecked()){
            monitor = editRhythm.getText().toString();
            editRhythm.setVisibility(View.VISIBLE);
        }else if(chkCardio.isChecked()){
            monitor = editCardio.getText().toString();
            editCardio.setVisibility(View.VISIBLE);
        }
        else if(chkPacing.isChecked()){
            //monitor = chkPacing.getText().toString();
            editPacing.setVisibility(View.VISIBLE);
            monitor = editPacing.getText().toString();
        }


        try{
            cardiacMonitoring.put("Cardiac Monitoring",monitor);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cardiacMonitoring;

    }


}
