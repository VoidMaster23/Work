package cape_medics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cape_medics.R;

import org.json.JSONObject;

public class BLSAid extends Fragment {

    ListView items;
    ListView items2;
    JSONObject BLS_ILSAidtoPatient;
    CheckBox oralAirway, needleCrich, O2Mask, eti, sga, nasalInt, manuelDefib, ioTherapy, ngt;
    EditText oralAirwayEdit, needleCrichEdit, O2MaskEdit, etiEdit,sgaEdit,nasalIntEdit, manuelDefibEdit, ioTherapyEdit,ngtEdit;

    public BLSAid(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_blsaid,container,false);
        BLS_ILSAidtoPatient = new JSONObject();
        items = view.findViewById(R.id.items);
        items2 = view.findViewById(R.id.items1);
        oralAirway = view.findViewById(R.id.oralChk);
        oralAirwayEdit = view.findViewById(R.id.oralEdit);
        needleCrich = view.findViewById(R.id.needleChk);
        needleCrichEdit = view.findViewById(R.id.needleEdit);
        O2Mask = view.findViewById(R.id.o2Chk);
        O2MaskEdit = view.findViewById(R.id.o2Edit);
        eti = view.findViewById(R.id.etiChk);
        etiEdit = view.findViewById(R.id.etiEdit);
        sga = view.findViewById(R.id.sgaChk);
        sgaEdit = view.findViewById(R.id.sgaEdit);
        nasalInt = view.findViewById(R.id.nasalChk);
        nasalIntEdit = view.findViewById(R.id.nasalEdit);
        manuelDefib = view.findViewById(R.id.manuelChk);
        manuelDefibEdit = view.findViewById(R.id.manuelEdit);
        ioTherapy = view.findViewById(R.id.ioChk);
        ioTherapyEdit = view.findViewById(R.id.ioEdit);
        ngt = view.findViewById(R.id.ngtChk);
        ngtEdit = view.findViewById(R.id.ngtEdit);

        items.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        items2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] aid = {"Abdominal Thrusts", "AED Applied", "Manuel Defib", "AED by Public","Dressing/Bandage", "BVM Ventilation", "Chest Thrusts", "CPR","KED","Hemorrhage Control","IV Therapy","PASG Applied", "Oral Glucose","IV Dextrose","SPO2 (Pulse Ox)", "Chest Decompression","Spinal Immob","Splints","Suction","Traction Splint","Drugs","Nebulisation"};
        String[] als = {"Cardioversion","Needle Crich","Surgical Crich","ECG","SP02 (Pulse Ox)","External Pacing","External Jug Cann.","Femoral Jug Cann.","Magills Forceps","Drugs"};

        ArrayAdapter<String> AidAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,aid);

        ArrayAdapter<String> AlsAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,als);

        items.setAdapter(AidAdapter);
        items2.setAdapter(AlsAdapter);

        return view;

    }

    public JSONObject createJson(){

        BLS_ILSAidtoPatient = new JSONObject();

        if(oralAirway.isChecked()) try{BLS_ILSAidtoPatient.put(oralAirway.getText().toString(),oralAirwayEdit.toString());}catch(Exception e){}
        if(needleCrich.isChecked()) try{BLS_ILSAidtoPatient.put(needleCrich.getText().toString(),needleCrichEdit.toString());}catch(Exception e){}
        if(O2Mask.isChecked()) try{BLS_ILSAidtoPatient.put(O2Mask.getText().toString(),O2MaskEdit.toString());}catch(Exception e){}
        if(eti.isChecked()) try{BLS_ILSAidtoPatient.put(eti.getText().toString(),etiEdit.toString());}catch(Exception e){}
        if(sga.isChecked()) try{BLS_ILSAidtoPatient.put(sga.getText().toString(),sgaEdit.toString());}catch(Exception e){}
        if(nasalInt.isChecked()) try{BLS_ILSAidtoPatient.put(nasalInt.getText().toString(),nasalIntEdit.toString());}catch(Exception e){}
        if(manuelDefib.isChecked()) try{BLS_ILSAidtoPatient.put(manuelDefib.getText().toString(),manuelDefibEdit.toString());}catch(Exception e){}
        if(ioTherapy.isChecked()) try{BLS_ILSAidtoPatient.put(ioTherapy.getText().toString(),ioTherapyEdit.toString());}catch(Exception e){}



        for (int i = 0; i < items.getChildCount(); i++) {

            if (items.isItemChecked(i)){
                try{
                    BLS_ILSAidtoPatient.put(items.getItemAtPosition(i).toString(),items.getItemAtPosition(i).toString());

                }catch (Exception e){}
            }
        }

        for (int i = 0; i < items2.getChildCount(); i++) {

            if (items2.isItemChecked(i)){
                try{
                    BLS_ILSAidtoPatient.put(items2.getItemAtPosition(i).toString(),items2.getItemAtPosition(i).toString());

                }catch (Exception e){}
            }
        }

        return BLS_ILSAidtoPatient;

    }
}
