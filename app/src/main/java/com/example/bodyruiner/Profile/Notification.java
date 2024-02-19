package com.example.bodyruiner.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.bodyruiner.R;

public class Notification extends AppCompatActivity {
    ImageView returnBtn;
    SwitchCompat emailSwitch,pushSwitch,updateSwitch;
    private static final String EMAIL_SWITCH_STATUS = "email_status";
    private static final String PUSH_SWITCH_STATUS = "push_status";
    private static final String UPDATE_SWITCH_STATUS = "update_status";

    boolean email_status;
    boolean push_status;
    boolean update_status;

    SharedPreferences emailPreference,pushPreference,updatePreference;
    SharedPreferences.Editor editor1,editor2,editor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_notification);
        getSupportActionBar().hide();

        emailSwitch = findViewById(R.id.emailSwitch);
        pushSwitch = findViewById(R.id.pushSwitch);
        updateSwitch = findViewById(R.id.updateSwitch);

        String EMAIL_SWITCH_PREF = "email_switch_pref";
        emailPreference = getSharedPreferences(EMAIL_SWITCH_PREF,MODE_PRIVATE);
        editor1 = getSharedPreferences(EMAIL_SWITCH_PREF,MODE_PRIVATE).edit();
        email_status = emailPreference.getBoolean(EMAIL_SWITCH_STATUS,false);

        String PUSH_SWITCH_PREF = "push_switch_pref";
        pushPreference = getSharedPreferences(PUSH_SWITCH_PREF,MODE_PRIVATE);
        editor2 = getSharedPreferences(PUSH_SWITCH_PREF,MODE_PRIVATE).edit();
        push_status = pushPreference.getBoolean(PUSH_SWITCH_STATUS,false);

        String UPDATE_SWITCH_PREF = "update_switch_pref";
        updatePreference = getSharedPreferences(UPDATE_SWITCH_PREF,MODE_PRIVATE);
        editor3 = getSharedPreferences(UPDATE_SWITCH_PREF,MODE_PRIVATE).edit();
        update_status = updatePreference.getBoolean(UPDATE_SWITCH_STATUS,false);


        emailSwitch.setChecked(email_status);
        emailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    editor1.putBoolean(EMAIL_SWITCH_STATUS,true);
                    editor1.apply();
                    emailSwitch.setChecked(true);
                }
                else{
                    editor1.putBoolean(EMAIL_SWITCH_STATUS,false);
                    editor1.apply();
                    emailSwitch.setChecked(false);
                }
            }
        });

        pushSwitch.setChecked(push_status);
        pushSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    editor2.putBoolean(PUSH_SWITCH_STATUS,true);
                    editor2.apply();
                    pushSwitch.setChecked(true);
                }
                else{
                    editor2.putBoolean(PUSH_SWITCH_STATUS,false);
                    editor2.apply();
                    pushSwitch.setChecked(false);
                }
            }
        });

        updateSwitch.setChecked(update_status);
        updateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    editor3.putBoolean(UPDATE_SWITCH_STATUS,true);
                    editor3.apply();
                    updateSwitch.setChecked(true);
                }
                else{
                    editor3.putBoolean(UPDATE_SWITCH_STATUS,false);
                    editor3.apply();
                    updateSwitch.setChecked(false);
                }
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