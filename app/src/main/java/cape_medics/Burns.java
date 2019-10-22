package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.R;

import org.json.JSONObject;

import java.text.DecimalFormat;


public class Burns extends Fragment {

    String[] burnType = {"Thermal(Fire, Water, Oil)","Chemical(Acid, etc)","Electrical","Lightning"};
    String[] treatment = {"IV","Burn Spray (Hydrogel)","Burn Dressing (Hydrogel)","Wed Dressing/Trauma Pad","Dry Dressing"};
    String[] inhalation = {"Facial Burns","Soot In Sputum","Breathing Difficulties"};
    String[] degree = {"1st","2nd (Partial Thickness)","3rd (Full Thickness)","4th (Involving Tendons)"};

    ListView type,dress,inhale,burn;

    TextView score;
    EditText weight, tsa;

    Button calc;

    CheckBox adult, child;
    String bType, bDress, bInhale, burnDeg;
    JSONObject burns;

    public Burns() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_burns, container, false);

        type = view.findViewById(R.id.burnType);
        dress = view.findViewById(R.id.treatment);
        inhale = view.findViewById(R.id.inhaleBurn);
        burn = view.findViewById(R.id.degree);
        adult = view.findViewById(R.id.chkAdult);
        child = view.findViewById(R.id.chkChild);

        ArrayAdapter a1 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,burnType);
        ArrayAdapter a2 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,treatment);
        ArrayAdapter a3 = new ArrayAdapter(getContext(), R.layout.custom_yes_no,inhalation);
        ArrayAdapter a4 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,degree);

        type.setAdapter(a1);
        dress.setAdapter(a2);
        inhale.setAdapter(a3);
        burn.setAdapter(a4);

        score = view.findViewById(R.id.txtInfusion);
        weight = view.findViewById(R.id.edtWeight);
        tsa  = view.findViewById(R.id.edtSurface);

        calc = view.findViewById(R.id.btnCalcapgar);

        calc.setOnClickListener(view1 -> {
            float weigh = Float.parseFloat(weight.getText().toString());
            float percent = Float.parseFloat(tsa.getText().toString());

            DecimalFormat format = new DecimalFormat("##.00");

            Float ans = new Float( 4*weigh*percent/1000);


            String put = "Infusion Answer: "+ format.format(ans)+"L";

            score.setText(put);
        });



        type.setOnItemClickListener((adapterView, view12, i, l) -> {
            CheckedTextView text  =  view12.findViewById(android.R.id.text1);
            bType = text.getText().toString();
            text.toggle();
        });

        dress.setOnItemClickListener((adapterView, view13, i, l) -> {
            CheckedTextView text  =  view13.findViewById(android.R.id.text1);
            bDress = text.getText().toString();
            text.toggle();
        });

        inhale.setOnItemClickListener((adapterView, view14, i, l) -> {
            CheckedTextView text  =  view14.findViewById(android.R.id.text1);
            bInhale = text.getText().toString();
            text.toggle();
        });

        burn.setOnItemClickListener((adapterView, view15, i, l) -> {
            CheckedTextView text  =  view15.findViewById(android.R.id.text1);
            burnDeg = text.getText().toString();
            text.toggle();
        });


        return view;
    }

    public JSONObject createJson(){
        burns = new JSONObject();
        String ageBurn = null;

        if(adult.isChecked()){
            ageBurn = adult.getText().toString();
        }else if(child.isChecked()){
            ageBurn = child.getText().toString();
        }

        String weigh = weight.getText().toString();
        String surf = tsa.getText().toString();
        String ans = score.getText().toString();

        try{
            burns.put("Age", ageBurn);
            burns.put("Burn Type", bType);
            burns.put("Dressing", bDress);
            burns.put("Inhalation", bInhale);
            burns.put("Degree of Burn", burnDeg);
            burns.put("Weight", weigh);
            burns.put("TSA", surf);
            burns.put("Answer", ans);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return burns;
    }

}
