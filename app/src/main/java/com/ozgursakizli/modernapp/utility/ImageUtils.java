package com.ozgursakizli.modernapp.utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ozgursakizli.modernapp.R;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class ImageUtils {

    public static void loadImage(ImageView iv, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(iv.getContext())
                .setDefaultRequestOptions(options)
                .load(url).into(iv);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }

}
