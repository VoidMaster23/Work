package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.cape_medics.R;

import org.json.JSONObject;

public class GuaranteeOfPayment extends Fragment {

    JSONObject guaranteeOfPayment;
    EditText name,company,positionHeld, company1;
    String authorisedPerson;
    CheckBox staff, passenger, person;


    public GuaranteeOfPayment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_guarantee_of_payment,container,false);
        guaranteeOfPayment = new JSONObject();

        name = view.findViewById(R.id.nameEdit);
        company = view.findViewById(R.id.companyEdit);
        positionHeld = view.findViewById(R.id.positionEdit);
        company1 = view.findViewById(R.id.company2Edit);

        staff = view.findViewById(R.id.staffChk);
        passenger = view.findViewById(R.id.passengerChk);
        person = view.findViewById(R.id.personChk);

        return view;
    }

    public JSONObject createJson(){
        guaranteeOfPayment = new JSONObject();

        if(staff.isChecked()) authorisedPerson = "Staff";
        if(passenger.isChecked()) authorisedPerson = "Passenger";
        if(person.isChecked()) authorisedPerson = "Person";

        try{
            guaranteeOfPayment.put("Full Name", name.toString());
            guaranteeOfPayment.put("Company Name", company.toString());
            guaranteeOfPayment.put("Position Held", positionHeld.toString());
            guaranteeOfPayment.put("Company Name", company1.toString());
            guaranteeOfPayment.put("Authorised Person", authorisedPerson);

        }catch(Exception e){}

        return guaranteeOfPayment;
    }
}
