package com.example.bodyruiner.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bodyruiner.Explore.ExploreMenu;
import com.example.bodyruiner.Profile.ProfileMenu;
import com.example.bodyruiner.R;
import com.example.bodyruiner.RecyclerViewInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HomeMenu extends AppCompatActivity implements RecyclerViewInterface {
    SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottomNavigationView;

    ArrayList<HomePostModel> homePostModels = new ArrayList<>();
    int[]artistPfp = {R.drawable.postauthorpfp1,R.drawable.postauthorpfp2,R.drawable.postauthorpfp3,
            R.drawable.postauthorpfp4,R.drawable.postauthorpfp5,R.drawable.postauthorpfp6,
            R.drawable.postauthorpfp7,R.drawable.postauthorpfp8,R.drawable.postauthorpfp9,R.drawable.styleartist2,
            R.drawable.styleartist1,R.drawable.styleartist3,R.drawable.styleartist4};

    int[]artistPosts = {R.drawable.tattooimg1,R.drawable.tattooimg2,R.drawable.tattooimg3,
            R.drawable.tattooimg4,R.drawable.tattooimg5,R.drawable.tattooimg6,R.drawable.tattooimg7,
            R.drawable.tattooimg8,R.drawable.tattooimg9,R.drawable.tatpost,R.drawable.tattpost2,
            R.drawable.tattpost3,R.drawable.tattpost4} ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);
        getSupportActionBar().hide();


        setArtistPostModels();

        RecyclerView recyclerView = findViewById(R.id.homeRecyclerView);

        HomeRVAdapter adapter = new HomeRVAdapter(this, homePostModels,this);
        recyclerView.setAdapter(adapter);
        adapter.setData(homePostModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Collections.shuffle(homePostModels);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },600);

            }
        });

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
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
                        startActivity(new Intent(getApplicationContext(), ProfileMenu.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });


    }
    public static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void setArtistPostModels(){
        String[] artistNames = getResources().getStringArray(R.array.post_author_names);

        for(int i = 0; i<artistNames.length;i++){
            homePostModels.add(new HomePostModel(artistNames[i], artistPfp[i], artistPosts[i]));
        }
        Collections.shuffle(homePostModels);
    }

    @Override
    public void likeClicked(int position) {
        String info = homePostModels.get(position).getPostAuthorName();
        Toast toast = Toast.makeText(this,"Liked "+info,Toast.LENGTH_SHORT);
        toast.show();
//

    }
    @Override
    public void dislikeClicked(int position) {
        ImageView like = findViewById(R.id.likeButton);
        like.setImageResource(R.drawable.ic_like);
    }


    @Override
    public void profileClicked(int position){
        String info = homePostModels.get(position).getPostAuthorName();
        Toast toast = Toast.makeText(this,"com.example.bodyruiner.Profile Clicked "+info,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void commentClicked(int position) {
        String info = homePostModels.get(position).getPostAuthorName();
        Toast toast = Toast.makeText(this,"Commented on "+info,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void favoriteClicked(int position) {
        String info = homePostModels.get(position).getPostAuthorName();
        Toast toast = Toast.makeText(this,"Add on favorites "+info,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void shareClicked(int position) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        startActivity(Intent.createChooser(intent,"ShareVia"));
    }

}