package com.example.bodyruiner.Explore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bodyruiner.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreStyleAdapter extends RecyclerView.Adapter<ExploreStyleAdapter.MyViewHolder>{

    private final ExploreRVInterface exploreRVInterface;
    private int lastPosition = -1;

    Context context;
    List<ExploreStyleModel> exploreStyleModels;


    public ExploreStyleAdapter(Context context, ArrayList<ExploreStyleModel> exploreItemsModels, ExploreRVInterface exploreItemInterface){
        this.context = context;
        this.exploreStyleModels = exploreItemsModels;
        this.exploreRVInterface = exploreItemInterface;
    }

    @NonNull
    @Override
    public ExploreStyleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.explore_style_rv_item,parent,false);

        return new ExploreStyleAdapter.MyViewHolder(view, exploreRVInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreStyleAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(exploreStyleModels.get(position).getStyleImage());
        holder.textView.setText(exploreStyleModels.get(position).getStyleName());
        setAnimation(holder.itemView,position);
    }

    public void setAnimation(View itemview,int position){
        if(position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.fade_in_explore);
            itemview.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return exploreStyleModels.size();
    }

    public void setData(List<ExploreStyleModel> newList){
        ExploreDiffUtil myDiffUtil = new ExploreDiffUtil(exploreStyleModels,newList);
        DiffUtil.DiffResult diffResult  = DiffUtil.calculateDiff(myDiffUtil);
        exploreStyleModels = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView, ExploreRVInterface exploreRVInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.styleImage);
            textView = itemView.findViewById(R.id.styleName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exploreRVInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            exploreRVInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}