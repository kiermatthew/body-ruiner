package com.example.bodyruiner.Explore;

import androidx.recyclerview.widget.DiffUtil;

import com.example.bodyruiner.Explore.ExploreStyleModel;

import java.util.List;

public class ExploreDiffUtil extends DiffUtil.Callback {

    private List<ExploreStyleModel> oldList;
    private List<ExploreStyleModel> newList;

    public ExploreDiffUtil(List<ExploreStyleModel> oldList, List<ExploreStyleModel> newList) {
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
        return oldList.get(oldItemPosition).getStyleName() == newList.get(newItemPosition).getStyleName();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if(!oldList.get(oldItemPosition).getStyleName().equals(newList.get(newItemPosition).getStyleName())){
            return false;
        }
        else if(oldList.get(oldItemPosition).getStyleImage() != newList.get(newItemPosition).getStyleImage()) {
            return false;
        }
        else
            return true;
    }
}
