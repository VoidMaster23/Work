package cape_medics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cape_medics.FireEngine;
import com.example.cape_medics.FireSkid;
import com.example.cape_medics.R;

public class FireSelect extends AppCompatActivity {
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_select);
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
    }

    public void Engine(View v){
        Intent i = new Intent(getApplicationContext(), FireEngine.class);
        i.putExtra("code",code);
        startActivity(i);
    }

    public void Skid(View v){
        Intent i = new Intent(getApplicationContext(), FireSkid.class);
        i.putExtra("code",code);
        startActivity(i);
    }
}
