package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ILoader extends AsyncTaskLoader {
    private String site;

    public ILoader(@NonNull Context context, String site) {
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
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;
        URL url = Urlmaker(site);
        InputStream inputStream = null;
        String JsonResponse = "";
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            int rc = urlConnection.getResponseCode();
            if (rc == 200) {
                inputStream = urlConnection.getInputStream();
                JsonResponse = insmaker(inputStream);


            }

        } catch (IOException e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }

            return JsonResponse;


        }


    }

    public String insmaker(InputStream i) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (i != null) {
            InputStreamReader isr = new InputStreamReader(i, Charset.forName("UTF-8"));
            BufferedReader bf = new BufferedReader(isr);
            String line = bf.readLine();
            while (line != null) {
                sb.append(line);
                line = bf.readLine();
            }
        }
        return sb.toString();
    }


    public URL Urlmaker(String s) {
        URL website = null;
        try {
            website = new URL(s);
        } catch (MalformedURLException e) {

        } finally {
            return website;
        }
    }
}