package com.ihminq.movie_hub.domain.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MovieHome {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterPath;

    public MovieHome() {}

    public MovieHome(int id, String title, float voteAvg, String releaseDate, String posterPath) {
        this.id = id;
        this.title = title;
        this.rating = voteAvg;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieHome movieHome = (MovieHome) o;

        if (id != movieHome.id) return false;
        if (Float.compare(movieHome.rating, rating) != 0) return false;
        if (!Objects.equals(title, movieHome.title)) return false;
        if (!Objects.equals(releaseDate, movieHome.releaseDate))
            return false;
        return Objects.equals(posterPath, movieHome.posterPath);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

}
