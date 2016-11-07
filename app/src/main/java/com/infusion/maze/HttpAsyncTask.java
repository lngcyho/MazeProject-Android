package com.infusion.maze;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by NGL8 on 11/6/2016.
 */

public class HttpAsyncTask extends AsyncTask<String, String, String> {
    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0) {
            Log.e(getClass().getName(), "There are no params in the async task. Proceed to do nothing");
            return null;
        }

        String url = params[0];
        String body = params[1];
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
