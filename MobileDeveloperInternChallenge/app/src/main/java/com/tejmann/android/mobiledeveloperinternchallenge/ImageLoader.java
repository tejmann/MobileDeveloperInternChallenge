package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageLoader extends AsyncTaskLoader {

    private String site;

    public ImageLoader(@NonNull Context context, String site) {
        super(context);
        this.site = site;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Object loadInBackground() {

        Bitmap bitmap;
        InputStream inputStream2= null;
        try {
            inputStream2 = new URL(site).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap= BitmapFactory.decodeStream(inputStream2);

        return bitmap;
    }

}
