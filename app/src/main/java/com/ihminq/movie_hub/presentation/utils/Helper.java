package com.ihminq.movie_hub.presentation.utils;

public class Helper {
    public static String getHello(String fullname) {
        if (fullname.trim().isEmpty()) {
            return "Hello";
        }

        String[] nameParts = fullname.split(" ");

        return "Hello " + nameParts[nameParts.length - 1];
    }
}
