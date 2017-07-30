package com.example.rayne.simples.network.retrofit.service;


import com.example.rayne.simples.network.retrofit.data.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by RayneSim on 17/07/29.
 */

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> listRepos(@Path("user") String user);
}
