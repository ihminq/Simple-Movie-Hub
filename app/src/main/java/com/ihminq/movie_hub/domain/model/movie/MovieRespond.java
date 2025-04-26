package com.ihminq.movie_hub.domain.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieRespond {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private ArrayList<MovieHome> results;

    public ArrayList<MovieHome> getResults() {
        return results;
    }
}
