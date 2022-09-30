package com.example.adminflowersvalley.model;

public class BannerModel {
    private String bannerId;
    private String mName;
    private String mImageUrl;

    public BannerModel() {
        //empty constructor needed
    }
    public BannerModel(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public BannerModel(String bannerId, String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        bannerId = bannerId;
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getId() {
        return bannerId;
    }

    public void setId(String id) {
        this.bannerId = id;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

}