package com.ihminq.movie_hub.presentation.movies.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.constant.APIConstants;
import com.ihminq.movie_hub.constant.ResConstants;
import com.squareup.picasso.Picasso;

public class MovieBindingAdapter {
    @BindingAdapter("moviePoster")
    public static void setImageResource(ImageView imageView, String imgPath) {
        Picasso.get().load(APIConstants.BASE_POSTER_URL + imgPath)
                .placeholder(ResConstants.IMG_POSTER_PLACEHOLDER)
                .error(ResConstants.IMG_POSTER_PLACEHOLDER)
                .into(imageView);
    }
}
