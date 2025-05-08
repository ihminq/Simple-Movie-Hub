package com.ihminq.movie_hub.presentation.utils;

import java.time.LocalDate;

public class Helper {
    public static String getHello(String fullname) {
        if (fullname.trim().isEmpty()) {
            return "Hello";
        }

        String[] nameParts = fullname.split(" ");

        return "Hello " + nameParts[nameParts.length - 1];
    }

    public static String getDisplayRating(float rating) {
        if (rating <= 0 || rating > 10) {
            return "0.0";
        }

        return String.format("%.1f", rating);
    }

    public static String getDisplayAge(LocalDate birthDate) {
        String suffix = " years old";
        if (birthDate == null) {
            return "null" + suffix;
        }
        else {
            return LocalDate.now().getYear() - birthDate.getYear() + suffix;
        }
    }

    public static String getDisplayGender(boolean gender) {
        return gender ? "Male" : "Female";
    }
}
