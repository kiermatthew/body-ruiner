package com.example.bodyruiner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bodyruiner.Home.HomeMenu;

import java.lang.reflect.Field;

public class WelcomeScreen extends AppCompatActivity {

    ViewPager slideViewPager;
    LinearLayout slideIndicator;
    TextView[] dots;
    SliderAdapter sliderAdapter;
    ImageView nextBtn,backBtn;
    Button getStarted;
    Animation fadeIn, fadeOut;
    Boolean isFinished;
//    TextView alreadyHaveAcc;

    Interpolator interpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        getSupportActionBar().hide();


        SharedPreferences preferences = getSharedPreferences ( "PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString( "FirstTimeInstall",  "");


        if (FirstTime.equals("Yes")) {
            //If application was opened for the first time
            Intent intent = new Intent(getApplicationContext(), HomeMenu.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
        }

        else {
            //Else...
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString( "FirstTimeInstall", "Yes");
            editor.apply();
        }


        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        slideIndicator = (LinearLayout) findViewById(R.id.slideIndicator);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.backBtn);
        getStarted = findViewById(R.id.getStarted);
//        alreadyHaveAcc = findViewById(R.id.alreadyHaveAcc);
//        alreadyHaveAcc.setVisibility(View.INVISIBLE);
        getStarted.setVisibility(View.INVISIBLE);
        backBtn.setVisibility(View.INVISIBLE);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        isFinished = false;
        interpolator = new AccelerateInterpolator();

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);

        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(slideViewPager.getContext(),interpolator);
            mScroller.set(slideViewPager,scroller);
        }catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }

        setUpIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(getItem(1),true);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(getItem(-1),true);
            }
        });

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, GettingStarted.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setUpIndicator(int position){
        dots = new TextView[3];
        slideIndicator.removeAllViews();


        for(int i = 0;  i< dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive));
            slideIndicator.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

            if(position<2) {
                getStarted.setVisibility(View.INVISIBLE);
//                alreadyHaveAcc.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
            }
            else{
                getStarted.setVisibility(View.VISIBLE);
                getStarted.setAnimation(fadeIn);
//                alreadyHaveAcc.setVisibility(View.VISIBLE);
//                alreadyHaveAcc.setAnimation(fadeIn);
                nextBtn.setVisibility(View.INVISIBLE);
                isFinished = true;
            }
            if(position>0){
                backBtn.setVisibility(View.VISIBLE);
            }else
                backBtn.setVisibility(View.INVISIBLE);

            if(isFinished){
                getStarted.setVisibility(View.VISIBLE);
//                alreadyHaveAcc.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private int getItem(int i){
        return slideViewPager.getCurrentItem() + i;
    }
}