package com.example.bodyruiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    int images[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
    };

    int titles[]={
            R.string.title1,
            R.string.title2,
            R.string.title3,
    };

    int description[]={
            R.string.dscrptn1,
            R.string.dscrptn2,
            R.string.dscrptn3,
    };
    int btn[]={

    };

    public SliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slideTitleImg = (ImageView) view.findViewById(R.id.introImg);
        TextView slideTitleTxt = (TextView)  view.findViewById(R.id.introTitle);
        TextView slideTitleDesc = (TextView)  view.findViewById(R.id.introDescription);

        slideTitleImg.setImageResource(images[position]);
        slideTitleTxt.setText(titles[position]);
        slideTitleDesc.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
