package com.example.bodyruiner.ExploreItems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bodyruiner.Explore.ExploreMenu;
import com.example.bodyruiner.R;

public class Fineline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineline);
        getSupportActionBar().hide();

        ImageView returnBtn;
        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExploreMenu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
            }
        });
    }
}