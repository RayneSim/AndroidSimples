package com.example.rayne.simples.network.retrofit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rayne.simples.R;
import com.example.rayne.simples.network.retrofit.data.User;
import com.example.rayne.simples.network.retrofit.service.ServiceGenerator;
import com.example.rayne.simples.network.retrofit.service.UserService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitWithRxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_with_rx_java);
        rxJavaCallback();

    }

    private void rxJavaCallback() {
        UserService userService = ServiceGenerator.createService(UserService.class);

        // this code is part of your activity/fragment
        Observable<User> observable = userService.me();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
