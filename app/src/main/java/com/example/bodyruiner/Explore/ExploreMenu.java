package com.example.bodyruiner.Explore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bodyruiner.ExploreItems.BlacknGray;
import com.example.bodyruiner.ExploreItems.Blackwork;
import com.example.bodyruiner.ExploreItems.Chicano;
import com.example.bodyruiner.ExploreItems.DarkArt;
import com.example.bodyruiner.ExploreItems.Dotwork;
import com.example.bodyruiner.ExploreItems.Fineline;
import com.example.bodyruiner.ExploreItems.Geometric;
import com.example.bodyruiner.ExploreItems.HandPoked;
import com.example.bodyruiner.ExploreItems.Japanese;
import com.example.bodyruiner.ExploreItems.Lettering;
import com.example.bodyruiner.ExploreItems.NeoTraditional;
import com.example.bodyruiner.ExploreItems.NewSchool;
import com.example.bodyruiner.Home.HomeMenu;
import com.example.bodyruiner.Profile.ProfileMenu;
import com.example.bodyruiner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExploreMenu extends AppCompatActivity implements ExploreRVInterface{
    BottomNavigationView bottomNavigationView;

    int[]styleImages = {R.drawable.black_gray,R.drawable.chicano,R.drawable.fineline,
            R.drawable.handpoked,R.drawable.japanese,R.drawable.blackwork,R.drawable.dotwork,
            R.drawable.geometric,R.drawable.darkart,R.drawable.lettering,R.drawable.neo_traditional ,R.drawable.new_school,
           };

    ArrayList<ExploreStyleModel> exploreStyleModels = new ArrayList<>();

    GridLayoutManager hlm1,hlm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_menu);
        getSupportActionBar().hide();
        setStyleNames();


        RecyclerView styleRecyclerView = findViewById(R.id.styleRecyclerview);
//        RecyclerView placeRecyclerView = findViewById(R.id.placeRecyclerview);

        hlm1 = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL, false);
        hlm2 =  new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);

        ExploreStyleAdapter exploreStyleAdapter = new ExploreStyleAdapter(this, exploreStyleModels,this);
        styleRecyclerView.setAdapter(exploreStyleAdapter);
        exploreStyleAdapter.setData(exploreStyleModels);
        styleRecyclerView.setLayoutManager(hlm1);


        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.explore_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.explore_menu:
                        return true;
                    case  R.id.home_menu:
                        startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.profile_menu:
                        startActivity(new Intent(getApplicationContext(), ProfileMenu.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }

    private void setStyleNames(){
        String[] styleNames = getResources().getStringArray(R.array.style_names);

        for(int i = 0; i<styleNames.length;i++){
            exploreStyleModels.add(new ExploreStyleModel(styleImages[i],styleNames[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Toast toast;
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(getApplicationContext(), BlacknGray.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), Chicano.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 2:
                intent = new Intent(getApplicationContext(), Fineline.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), HandPoked.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 4:
                intent = new Intent(getApplicationContext(), Japanese.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 5:
                intent = new Intent(getApplicationContext(), Blackwork.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 6:
                intent = new Intent(getApplicationContext(), Dotwork.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 7:
                intent = new Intent(getApplicationContext(), Geometric.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 8:
                intent = new Intent(getApplicationContext(), DarkArt.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 9:
                intent = new Intent(getApplicationContext(), Lettering.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 10:
                intent = new Intent(getApplicationContext(), NeoTraditional.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
            case 11:
                intent = new Intent(getApplicationContext(), NewSchool.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                break;
        }


    }

}