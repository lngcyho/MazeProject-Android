package com.infusion.maze;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity {

    @BindView(R.id.up_button)
    Button upButton;

    @BindView(R.id.scan_button)
    Button scanButton;

    @BindView(R.id.down_button)
    Button downButton;

    @BindView(R.id.left_button)
    Button leftButton;

    @BindView(R.id.right_button)
    Button rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);
    }

    public void checkServerHealth() {
        Log.d(getClass().getName(), "Checking server health");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String host = getString(R.string.url_backend_host);
                String path = getString(R.string.path_health_check);

                Request request = new Request.Builder().url(host + path).get().build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(getClass().getName(), "HEALTH CHECK : " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void moveOneStepUp(View view) {
        Log.d(getClass().getName(), "Move up one step");
        HttpAsyncTask asyncTask = new HttpAsyncTask();
        asyncTask.execute();
    }

    public void scanArea(View view) {
        Log.d(getClass().getName(), "scan");
    }

    public void moveOneStepDown(View view) {
        Log.d(getClass().getName(), "Move down one step");
    }

    public void moveOneStepLeft(View view) {
        Log.d(getClass().getName(), "Move left one step");
    }

    public void moveOneStepRight(View view) {
        Log.d(getClass().getName(), "Move right one step");
    }
}
