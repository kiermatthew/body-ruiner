package com.example.bodyruiner.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bodyruiner.R;

public class Email extends AppCompatActivity {
    EditText newEmail;
    Button saveChanges;
    ImageView returnBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_email);
        getSupportActionBar().hide();

        newEmail = findViewById(R.id.editEmail);
        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String defaultEmail = preferences.getString("EMAIL","");
        newEmail.setHint(defaultEmail);

        saveChanges = findViewById(R.id.saveChangesBtn);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmailAdd = newEmail.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("EMAIL");
                editor.putString("EMAIL",newEmailAdd);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });

        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });
    }
}