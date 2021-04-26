package com.example.apichat.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ScreenOne {

    @SerializedName("Favorites")
    private ArrayList<Information> Favorites;

    @SerializedName("Recent")
    private ArrayList<Information> Recent;

    public List<Information> getFavorites() {
        return Favorites;
    }

    public void setFavorites(ArrayList<Information> favorites) {
        Favorites = favorites;
    }

    public ArrayList<Information> getRecent() {
        return Recent;
    }

    public void setRecent(ArrayList<Information> recent) {
        Recent = recent;
    }
}
