package com.example.bodyruiner.Home;

public class HomePostModel {
    String postAuthorName;
    int postAuthorPfp;
    int postAuthorImg;

    public HomePostModel(String artistName, int artistPfp, int artistPost) {
        this.postAuthorName = artistName;
        this.postAuthorPfp = artistPfp;
        this.postAuthorImg = artistPost;

    }



    public String getPostAuthorName() {
        return postAuthorName;
    }

    public int getPostAuthorPfp() {
        return postAuthorPfp;
    }

    public int getPostAuthorImg() {
        return postAuthorImg;
    }
}



