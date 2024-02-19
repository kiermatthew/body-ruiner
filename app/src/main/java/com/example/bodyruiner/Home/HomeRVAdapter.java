package com.example.bodyruiner.Home;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bodyruiner.R;
import com.example.bodyruiner.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<HomePostModel> homePostModels;
    private int lastPosition = -1;

    public HomeRVAdapter(Context context, List<HomePostModel> artistPostModels,RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.homePostModels = artistPostModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HomeRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_rv_item,parent,false);

        return new HomeRVAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVAdapter.MyViewHolder holder, int position) {
        holder.postAuthorName.setText(homePostModels.get(position).getPostAuthorName());
        holder.postAuthorProfile.setImageResource(homePostModels.get(position).getPostAuthorPfp());
        holder.postImage.setImageResource(homePostModels.get(position).getPostAuthorImg());

        setAnimation(holder.itemView,position);
    }

    public void setAnimation(View itemview,int position){
        if(position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.fade_in_rv);
            itemview.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return homePostModels.size();
    }


    public void setData(List<HomePostModel> newList){
        MyDiffUtil myDiffUtil = new MyDiffUtil(homePostModels,newList);
        DiffUtil.DiffResult diffResult  = DiffUtil.calculateDiff(myDiffUtil);
        homePostModels = newList;
        diffResult.dispatchUpdatesTo(this);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView postAuthorProfile, postImage, shareButton, likeButton, favoriteButton;
        TextView postAuthorName;
        LinearLayout postUserContainer;
        Boolean isClicked = false;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            postAuthorProfile = itemView.findViewById(R.id.postAuthorProfilePicture);
            postImage = itemView.findViewById(R.id.postImage);
            postAuthorName = itemView.findViewById(R.id.postAuthorName);
            likeButton = itemView.findViewById(R.id.likeButton);
            postUserContainer = itemView.findViewById(R.id.postUserContainer);
            shareButton = itemView.findViewById(R.id.shareButton);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            if(isClicked){
                                likeButton.setImageResource(R.drawable.ic_liked);
                                isClicked=false;
                            }
                            else{
                                likeButton.setImageResource(R.drawable.ic_like);
                                isClicked=true;
                            }

                        }
                    }
                }
            });


            postUserContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.profileClicked(pos);
                        }
                    }
                }
            });

            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.shareClicked(pos);
                        }
                    }
                }
            });

        }
    }
}
