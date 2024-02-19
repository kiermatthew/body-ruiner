package com.example.bodyruiner.Home;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyDiffUtil extends DiffUtil.Callback {

    private List<HomePostModel> oldList;
    private List<HomePostModel> newList;

    public MyDiffUtil(List<HomePostModel> oldList, List<HomePostModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).postAuthorName == newList.get(newItemPosition).postAuthorName;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
       if(!oldList.get(oldItemPosition).postAuthorName.equals(newList.get(newItemPosition).postAuthorName)){
           return false;
       }
       else if(oldList.get(oldItemPosition).postAuthorImg != newList.get(newItemPosition).postAuthorImg) {
           return false;
       }
       else if(oldList.get(oldItemPosition).postAuthorPfp != newList.get(newItemPosition).postAuthorPfp){
           return false;
       }
       else
           return true;
    }
}
