package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.R;

import org.json.JSONObject;


public class PainScore extends Fragment {

    EditText pre, post1, post2, hosp;

    private Spinner spnPre, spnPost1,spnPost2,spnHosp;
    Button show;

    TextView textView1, textView2, textView3, textView4;;

    JSONObject painScore;

    String sizeType;

    private String[] size = {"1","2","3","4","5","6","7","8","9","10"};

    public PainScore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_score, container, false);


        spnPre = view.findViewById(R.id.editPre);
        spnPost1 = view.findViewById(R.id.editPost5);
        spnPost2 = view.findViewById(R.id.editPost10);
        spnHosp = view.findViewById(R.id.editAtHosp);

        textView1 = view.findViewById(R.id.textView51);
        textView2 = view.findViewById(R.id.textView52);
        textView3 = view.findViewById(R.id.textView53);
        textView4 = view.findViewById(R.id.textView54);

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

        return view;
    }

    public JSONObject createJson(){

        painScore = new JSONObject();

        String preStr = pre.getText().toString();
        String post5Str = post1.getText().toString();
        String post10Str = post2.getText().toString();
        String hospStr = hosp.getText().toString();

        try{
            painScore.put("Pre-Analgesia",preStr);
            painScore.put("Post 5",post5Str);
            painScore.put("Post 10",post10Str);
            painScore.put("Hospital",hospStr);


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return painScore;
    }

}
