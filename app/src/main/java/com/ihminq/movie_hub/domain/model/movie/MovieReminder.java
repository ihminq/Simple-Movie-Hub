package com.ihminq.movie_hub.domain.model.movie;


public class MovieReminder {
    private int reminderId;
    private int movieId;
    private String movieTitle;
    private float rating;
    private String releaseDate;
    private String posterPath;

    public MovieReminder() {

    }

    public MovieReminder(int reminderId, int movieId, String movieTitle, float rating, String releaseDate, String posterPath) {
        this.reminderId = reminderId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
