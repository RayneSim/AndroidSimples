package com.example.rayne.simples.network.retrofit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rayne.simples.R;
import com.example.rayne.simples.network.retrofit.service.FileDownloadService;
import com.example.rayne.simples.network.retrofit.service.ServiceGenerator;
import com.orhanobut.logger.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicBaseUrlActivity extends AppCompatActivity {

    public static final String TAG = "CallInstances";
    private Callback<ResponseBody> downloadCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_base_url);

        downloadCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Logger.d(TAG, "server contacted at: " + call.request().url());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.d(TAG, "call failed against the url: " + call.request().url());
            }
        };

        // first request
        FileDownloadService downloadService = ServiceGenerator.createService(FileDownloadService.class);
        Call<ResponseBody> originalCall = downloadService.downloadFileWithFixedUrl();
        originalCall.enqueue(downloadCallback);

        // change base url
        ServiceGenerator.changeApiBaseUrl("http://development.futurestud.io/api");

        // new request against new base url
        FileDownloadService newDownloadService = ServiceGenerator.createService(FileDownloadService.class);
        Call<ResponseBody> newCall = newDownloadService.downloadFileWithFixedUrl();
        newCall.enqueue(downloadCallback);



    }
}
