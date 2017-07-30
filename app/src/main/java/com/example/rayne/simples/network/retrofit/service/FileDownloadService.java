package com.example.rayne.simples.network.retrofit.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RayneSim on 17/07/30.
 */

public interface FileDownloadService {

    @GET("/api")
    public Call<ResponseBody> downloadFileWithFixedUrl();
}
