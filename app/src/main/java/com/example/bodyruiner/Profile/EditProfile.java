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

import com.example.bodyruiner.Home.HomeMenu;
import com.example.bodyruiner.R;

public class EditProfile extends AppCompatActivity {
    EditText accName,confirmPW;
    ImageView returnBtn;
    Button saveChangesBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        getSupportActionBar().hide();


        accName = findViewById(R.id.editName);
        confirmPW = findViewById(R.id.confirmEditName);
        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);



        String name = preferences.getString("NAME","");
        accName.setHint(name);

        String password = preferences.getString("PASSWORD","");

        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileMenu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });

        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accName.getText().toString().equals("")&&confirmPW.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"No input found!",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(accName.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(confirmPW.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    if(!confirmPW.getText().toString().equals(password)){
                        Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Password!",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        String newName = accName.getText().toString();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("NAME");
                        editor.putString("NAME",newName);
                        editor.apply();
                        Toast toast = Toast.makeText(getApplicationContext(),"Name Changed",Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(), ProfileMenu.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                    }
                }

            }
        });

    }
}