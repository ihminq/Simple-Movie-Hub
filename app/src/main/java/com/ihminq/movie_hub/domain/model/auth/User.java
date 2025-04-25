package com.ihminq.movie_hub.domain.model.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;

public class User {
    private String uid;
    private String fullname;
    private String email;
    private String password;
    @Nullable
    private LocalDate birthDate;

    public User() {}

    public User(String uid, String fullname, String email, String password) {
        this.uid = uid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
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

    @Nullable
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Nullable LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
