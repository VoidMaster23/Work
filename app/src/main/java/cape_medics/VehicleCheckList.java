package cape_medics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cape_medics.Ambulance;
import com.example.cape_medics.FireSelect;
import com.example.cape_medics.R;

public class VehicleCheckList extends AppCompatActivity {

    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_check_list);
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
    }

    public void Amb(View v){
        Intent i = new Intent(getApplicationContext(), Ambulance.class);
        i.putExtra("code",code);
        startActivity(i);
    }

    public void Fire(View v){
        Intent i = new Intent(getApplicationContext(), FireSelect.class);
        i.putExtra("code",code);
        startActivity(i);
    }
}
