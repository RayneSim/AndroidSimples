package com.example.rayne.simples.network.retrofit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rayne.simples.R;
import com.example.rayne.simples.network.retrofit.data.Task;
import com.example.rayne.simples.network.retrofit.data.User;
import com.example.rayne.simples.network.retrofit.service.ServiceGenerator;
import com.example.rayne.simples.network.retrofit.service.TaskService;
import com.example.rayne.simples.network.retrofit.service.UserService;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncAsyncActivity extends AppCompatActivity {

    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_async);
        userService = ServiceGenerator.createService(UserService.class);
        exeSyncMethod();
        exeSyncMethod2();
        exeAsyncMethod();
        exeAsyncMethod2();
    }

    private void exeSyncMethod() {
        // synchronous
        Call<User> call = userService.login();
        try {
            User user = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // changed your mind, cancel the request
        call.cancel();
    }

    private void exeSyncMethod2(){
        TaskService taskService = ServiceGenerator.createService(TaskService.class);
        Call<List<Task>> call = taskService.getTasks();
        try {
            List<Task> tasks = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exeAsyncMethod() {
        // asynchronous
        Call<User> call = userService.login();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // response.isSuccessful() is true if the response code is 2xx
                if (response.isSuccessful()) {
                    User user = response.body();
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // handle execution failures like no internet connectivity
            }
        });
    }

    private void exeAsyncMethod2(){

        TaskService taskService = ServiceGenerator.createService(TaskService.class);
        Call<List<Task>> call = taskService.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                // get raw response
                okhttp3.Response raw = response.raw();

                if (response.isSuccessful()) {
                    // tasks available

                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                // something went completely south (like no internet connection)
                Logger.d("Error", t.getMessage());
            }
        });
    }
}
