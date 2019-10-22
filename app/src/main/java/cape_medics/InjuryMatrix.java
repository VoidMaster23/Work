package cape_medics;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

import java.io.InputStream;

import static com.example.cape_medics.DrugsAdministered.StreamToString;


public class InjuryMatrix extends Fragment {
    String[] causes;
    String[] injuryType = {"Abrasion","Amputation","Blunt Injury","Burn","Crushing Injury","Dislocation/Fracture","Gunshot Wound","Laceration","Pain","Puncture/Stab","Tissue Swelling"};
    String[] bodyPart = {"Head","Face","Neck","Chest","Back","Abdomen","Pelvic/Genitalia","Upper Extremity","Lower Extremity"};

    private TextView textView31;
    private Spinner spinner2;
    private TextView textView32;
    private Spinner spinner3;
    private TextView textView33;
    private Spinner spinner7;

    String cause, injury, part;

    JSONObject injuryMatrix;






    public InjuryMatrix() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_injury_matrix, container, false);


        spinner2 = (Spinner)view.findViewById( R.id.spinner2 );
        spinner3 = (Spinner)view.findViewById( R.id.spinner3 );
        spinner7 = (Spinner)view.findViewById( R.id.spinner7 );

        String filename = null;

        try{
            filename = "causeofinjury.txt";
            InputStream in = getContext().getAssets().open(filename, AssetManager.ACCESS_BUFFER);
            String sHTML = StreamToString(in);
            in.close();

            causes = sHTML.split("\n");
            ArrayAdapter adapter1 = new ArrayAdapter(getContext(), R.layout.custom_spinner,causes);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner7.setAdapter(adapter1);

            ArrayAdapter adapter2 = new ArrayAdapter(getContext(), R.layout.custom_spinner,injuryType);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner2.setAdapter(adapter2);

            ArrayAdapter adapter3 = new ArrayAdapter(getContext(), R.layout.custom_spinner,bodyPart);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner3.setAdapter(adapter3);


            spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    cause = causes[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    injury = injuryType[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    part = bodyPart[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }






        return view;
    }

    public JSONObject createJson(){
        injuryMatrix = new JSONObject();

        try{
            injuryMatrix.put("Injury",injury+", "+part);
            injuryMatrix.put("Cause",cause);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return injuryMatrix;

    }


}
