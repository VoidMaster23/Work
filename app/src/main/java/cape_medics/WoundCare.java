package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cape_medics.R;

import org.json.JSONObject;


public class WoundCare extends Fragment {

    String[] woundType = {"Abrasion","Laceration","Puncture","Avulsion","De-Gloving"};
    String[] treatedWith = {"Saline","Cetrimide","Alcohol Prep Pad","Iodine Solution","Anti Bacterial Cream"};
    String[] barrier = {"Strip(steri-strip etc)","Adhesive Plaster (strip or anchor)","Film Barrier (Tergaderm)","Island Dressing","FAD","Cohesive/Elastic Bandage"};

    ListView lisWound, lisTreat, lisBarrier;
    String woundStr, treatStr, barrierStr;

    JSONObject woundCare;

    public WoundCare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wound_care, container, false);

        lisWound = view.findViewById(R.id.woundType);
        lisTreat = view.findViewById(R.id.treatedWith);
        lisBarrier = view.findViewById(R.id.barrier);

        ArrayAdapter a1 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,woundType);
        ArrayAdapter a2 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,treatedWith);
        ArrayAdapter a3 = new ArrayAdapter(getContext(), R.layout.custom_checked_list,barrier);

        lisWound.setAdapter(a1);
        lisTreat.setAdapter(a2);
        lisBarrier.setAdapter(a3);

        lisWound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                woundStr = text.getText().toString();
                text.toggle();
            }
        });

        lisBarrier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                barrierStr = text.getText().toString();

                text.toggle();
            }
        });

        lisTreat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                treatStr = text.getText().toString();
                text.toggle();
            }
        });
        return view;
    }

    public JSONObject createJson(){

        woundCare = new JSONObject();

       try{
           woundCare.put("Wound Type",woundStr);
           woundCare.put("Barrier",barrierStr);
           woundCare.put("Treated With",treatStr);
       }catch (Exception e){
           Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
       }
       return woundCare;
    }

}
