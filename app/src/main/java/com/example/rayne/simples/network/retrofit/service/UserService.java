package com.example.rayne.simples.network.retrofit.service;

import com.example.rayne.simples.network.retrofit.data.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by RayneSim on 17/07/30.
 */

public interface UserService {
    @POST("/me")
    Call<User> login();

    @POST("/me")
    Observable<User> me();
}
