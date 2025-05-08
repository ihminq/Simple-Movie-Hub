package com.ihminq.movie_hub.domain.model.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ihminq.movie_hub.domain.model.movie.MovieHome;
import com.ihminq.movie_hub.domain.model.movie.MovieReminder;

import java.time.LocalDate;
import java.util.List;

public class User {
    private String uid;
    private String fullname;
    private String email;
    private String password;
    private boolean gender;
    @Nullable
    private LocalDate birthDate;
    @Nullable
    private String profileImageUrl;
    @Nullable
    private List<MovieHome> watchlist;
    @Nullable
    private List<MovieReminder> reminderList;

    public User() {}

    public User(String uid, String fullname, String email, String password) {
        this.uid = uid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = false;
    }

    @NonNull
    @Override
    public String toString() {
        return "Uid: " + this.uid + " birthdate: " + this.birthDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Nullable
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Nullable LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Nullable
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(@Nullable String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Nullable
    public List<MovieHome> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(@Nullable List<MovieHome> watchlist) {
        this.watchlist = watchlist;
    }

    @Nullable
    public List<MovieReminder> getReminderList() {
        return reminderList;
    }

    public void setReminderList(@Nullable List<MovieReminder> reminderList) {
        this.reminderList = reminderList;
    }

    public int getWatchlistCount() {
        return watchlist == null ? 0 : watchlist.size();
    }

    public int getRemidnersCount() {
        return reminderList == null ? 0 : reminderList.size();
    }
}
