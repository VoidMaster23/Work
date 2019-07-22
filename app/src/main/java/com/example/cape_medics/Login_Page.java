package com.example.cape_medics;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Login_Page extends AppCompatActivity {
    JSONObject loginPage;
    Button login;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        loginPage = new JSONObject();
        login = findViewById(R.id.LoginButton);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        username.setPaintFlags(0);
        password.setPaintFlags(0);


    }

    public void Login (View v){
        //add code to check username and password here (add else disp toast) and decide whether manager or crew
        try{
            loginPage.put("Username",username);
            loginPage.put("Password",password);
        }catch(Exception e){}



        if (username.getText().toString().trim().equals("manager")){
            Intent i = new Intent(getApplicationContext(), Home_Screen_Manager.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
