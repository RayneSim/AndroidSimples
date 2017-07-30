package com.example.rayne.simples.network.retrofit.service;

import com.example.rayne.simples.network.retrofit.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RayneSim on 17/07/30.
 */

public interface TaskService {
    @GET("/tasks")
    Call<List<Task>> getTasks();
}
