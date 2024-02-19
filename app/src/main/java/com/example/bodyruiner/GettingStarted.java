package com.example.bodyruiner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bodyruiner.Home.HomeMenu;

public class GettingStarted extends AppCompatActivity {
    Button continueBtn;
    EditText accountNameSignup,emailSignup,passowrdSignup,confirmpwsignup;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);
        getSupportActionBar().hide();

        accountNameSignup = findViewById(R.id.nameSignup);
        continueBtn = findViewById(R.id.continueBtn);
        emailSignup = findViewById(R.id.emailSignup);
        passowrdSignup = findViewById(R.id.passwordSignup);
        confirmpwsignup = findViewById(R.id.confirmpwSignup);
        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please answer required field.",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{

                    String password = passowrdSignup.getText().toString();
                    String confirmPW = confirmpwsignup.getText().toString();
                    Toast toast;
                    if(!password.equals(confirmPW)){
                        toast = Toast.makeText(getApplicationContext(), "Password don't match!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        String name = accountNameSignup.getText().toString();
                        String email = emailSignup.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("NAME",name);
                        editor.putString("EMAIL",email);
                        editor.putString("PASSWORD",password);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), HomeMenu.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                    }
                }
            }
        });


    }
    public boolean isEmpty(){
        return accountNameSignup.getText().toString().equals("") || emailSignup.getText().toString().equals("")
                || passowrdSignup.getText().toString().equals("") || confirmpwsignup.getText().toString().equals("");
    }
}