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

import java.util.ArrayList;


public class AirwayBreathing extends Fragment {

    ArrayList<String> myList;
    ListView listView;
    JSONObject airwayBreathing;
    String management;
    public AirwayBreathing() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_airway_breathing, container, false);

        myList = new ArrayList<String>();
        myList.add("Traci Mask");
        myList.add("Venturi Mask 40%");
        myList.add("N.R. Mask");
        myList.add("Nebuliser Mask");
        myList.add("In Line Web");
        myList.add("Nasal Cannulae");
        myList.add("BVM");
        myList.add("Flow Rate");

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.custom_checked_list, myList);

        listView = view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);
                management = text.getText().toString();
                text.toggle();
            }
        });



        return view;
    }

    public JSONObject createJson(){
        airwayBreathing = new JSONObject();
        try{
            airwayBreathing.put("Management",management);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return airwayBreathing;
    }

}
