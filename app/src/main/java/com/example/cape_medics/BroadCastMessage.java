package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BroadCastMessage extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_message);

        editText = findViewById(R.id.editText6);
    }

    public void Send(View v){
        //broad cast to all those chosen
        String Text = editText.getText().toString();

        Toast.makeText(this, "Broadcast Sent",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        i.putExtra("first","not");
        startActivity(i);
    }

    public void Cancel(View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        i.putExtra("first","not");
        startActivity(i);
    }
}
