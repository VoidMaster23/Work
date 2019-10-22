package cape_medics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.cape_medics.R;
import com.example.cape_medics.Signature;

import org.json.JSONObject;

public class RefusalofCare extends Fragment {
    Button go1, go2;
    JSONObject refusalOfCare;
    CheckBox seen, treated, examined, option1, option2, option3, option4;
    EditText name,contactNumber;
    String observationType, option,statement;
    public RefusalofCare(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_refusalof_care,container,false);
        refusalOfCare = new JSONObject();

        seen = view.findViewById(R.id.seenChk);
        treated = view.findViewById(R.id.treatedChk);
        examined = view.findViewById(R.id.examinedChk);
        option1 = view.findViewById(R.id.firstChk);
        option2 = view.findViewById(R.id.secondChk);
        option3 = view.findViewById(R.id.thirdChk);
        option4 = view.findViewById(R.id.fourthChk);
        contactNumber = view.findViewById(R.id.contactEdit);

        go1 = view.findViewById(R.id.signatureButton);
        go2 = view.findViewById(R.id.witnessButton);

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Patient Signature ");
                startActivity(i);
            }
        });

        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Witness Signature");
                startActivity(i);
            }
        });
        return view;
    }

    public JSONObject createJson(){
        refusalOfCare = new JSONObject();

        if(seen.isChecked()) observationType = "seen";
        if(examined.isChecked()) observationType = "examined";
        if(treated.isChecked()) observationType = "treated";

        if(option1.isChecked()) {
            option = "First Option";
            statement = option1.getText().toString();
        }
        if(option2.isChecked()) {
            option = "Second Option";
            statement = option2.getText().toString();
        }
        if(option3.isChecked()){
            option = "Third Option";
            statement = option3.getText().toString();
        }
        if(option4.isChecked()){
            option = "Fourth Option";
            statement = option4.getText().toString();
        }

        try{
            refusalOfCare.put("Observation Method", observationType);
            refusalOfCare.put(option, statement);
            refusalOfCare.put("Patient Name", name);
            refusalOfCare.put("Contact Number", contactNumber);

        }catch (Exception e){}

        return refusalOfCare;
    }
}
