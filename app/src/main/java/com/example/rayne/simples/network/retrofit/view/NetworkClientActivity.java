package com.example.rayne.simples.network.retrofit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rayne.simples.R;
import com.example.rayne.simples.network.retrofit.data.GitHubRepo;
import com.example.rayne.simples.network.retrofit.service.GitHubClient;
import com.example.rayne.simples.network.retrofit.service.ServiceGenerator;
import com.orhanobut.logger.Logger;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClientActivity extends AppCompatActivity {

    private static final String TAG = "NetworkClientActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_network_client);

        //创建网络请求对象
        // Create a very simple REST adapter which points the GitHub API endpoint.
//        GitHubClient client = retrofit.create(GitHubClient.class);
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call = client.reposForUser("fs-opensource");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>(){
            @Override
            public void onResponse
                    (Call < List < GitHubRepo >> call, Response < List < GitHubRepo >> response){
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                Logger.d(TAG, "success response = " + response);

            }

            @Override
            public void onFailure (Call < List < GitHubRepo >> call, Throwable t){
                // the network call was a failure
                // TODO: handle error
                Logger.d(TAG, "Error occurred , handle error");
            }
        });
    }


}
