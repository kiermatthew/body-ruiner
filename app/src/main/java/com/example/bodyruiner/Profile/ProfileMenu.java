package com.example.bodyruiner.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bodyruiner.Explore.ExploreMenu;
import com.example.bodyruiner.GettingStarted;
import com.example.bodyruiner.Home.HomeMenu;
import com.example.bodyruiner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileMenu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView accountName, editProfileBtn,settingBtn,helpSupport,logoutBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_menu);
        getSupportActionBar().hide();

        accountName = findViewById(R.id.accountName);
        bottomNavigationView = findViewById(R.id.bottomNav);
        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String name = preferences.getString("NAME","");
        accountName.setText(name);


        bottomNavigationView.setSelectedItemId(R.id.profile_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.explore_menu:
                        startActivity(new Intent(getApplicationContext(), ExploreMenu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.home_menu:
                        startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.profile_menu:
                        return true;
                }

                return false;
            }
        });

        editProfileBtn = findViewById(R.id.editProfileBtn);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });

        settingBtn = findViewById(R.id.settingsBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });

        helpSupport = findViewById(R.id.helpBtn);
        helpSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"This service is unavailable right now!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }
    private void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottompopup);

        Button yesButton = dialog.findViewById(R.id.yes);
        Button noButton = dialog.findViewById(R.id.no);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"You have been logged out!",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), GettingStarted.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Canceled lgout",Toast.LENGTH_SHORT);
                toast.show();
                dialog.cancel();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}