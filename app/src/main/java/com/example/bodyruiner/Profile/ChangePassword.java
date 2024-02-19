package com.example.bodyruiner.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bodyruiner.R;

public class ChangePassword extends AppCompatActivity {
    ImageView returnBtn;
    EditText newPassword, confirmNewPW;
    Button saveChanges;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_change_password);
        getSupportActionBar().hide();

        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });


        newPassword = findViewById(R.id.editPassword);
        confirmNewPW = findViewById(R.id.confirmEditPassword);
        saveChanges = findViewById(R.id.saveNewPWBtn);
        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);


        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPW = newPassword.getText().toString();
                String confirmPW = confirmNewPW.getText().toString();

                if(newPW.equals(confirmPW)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("PASSWORD");
                    editor.putString("PASSWORD",newPW);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), Settings.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Password don't match",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}