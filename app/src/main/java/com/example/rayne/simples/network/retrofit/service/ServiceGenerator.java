package com.example.rayne.simples.network.retrofit.service;

import com.wealdtech.hawk.HawkCredentials;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Creating a Sustainable Android Client
 *
 * the Retrofit object and its builder are the heart of all requests.
 * Here you configure and prepare your requests, responses, authentication, logging and error handling.
 *
 * Why Is Everything Declared Static Within the ServiceGenerator?
 *
 * we want to use the same objects (OkHttpClient, Retrofit, …) throughout the app to just open one
 * socket connection that handles all the request and responses including caching and many more.
 * It’s common practice to just use one OkHttpClient instance to reuse open socket connections.
 * That means, we either need to inject the OkHttpClient to this class via dependency injection
 * or use a static field.As you can see, we chose to use the static field.
 * And because we use the OkHttpClient throughout this class, we need to make all fields and methods static.
 * Created by RayneSim on 17/07/30.
 */
public class ServiceGenerator {


    private static String apiBaseUrl  = "http://futurestud.io/api";
//    private static Retrofit retrofit;

    private static final String BASE_URL_GITHUB = "https://api.github.com/";

    //配置Retrofit
    //The order in which you specify the converters matters!轉換器的順序很重要
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL_GITHUB)
                    .addConverterFactory(GsonConverterFactory.create());

    //真正构造Retrofit对象
//    Retrofit retrofit = builder
//            .client(httpClient.build())//see the comments top
//            .build();
    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    //配置okHttpClient
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();



    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }

    /**
     * The createService method takes a serviceClass, which is the annotated interface for API requests,
     * as a parameter and creates a usable client from it.
     * On the resulting client you'll be able to execute your network requests.
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceWithInterceptor(
            Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    /**
     * Prepare Authentication
     * You need to pass additional parameters to createService to create a client.
     * Let's look at an example for Hawk authentication:
     * @param serviceClass
     * @param credentials
     * @param <S>
     * @return
     */
    public static <S> S createServiceWithCredentials(
            Class<S> serviceClass, final HawkCredentials credentials) {
//        if (credentials != null) {
//            HawkAuthenticationInterceptor interceptor =
//                    new HawkAuthenticationInterceptor(credentials);
//
//            if (!httpClient.interceptors().contains(interceptor)) {
//                httpClient.addInterceptor(interceptor);
//
//                builder.client(httpClient.build());
//                retrofit = builder.build();
//            }
//        }

        return retrofit.create(serviceClass);
    }

//    public static <S> S createService(Class<S> serviceClass, AccessToken token) {
//        String authToken = token.getTokenType().concat(token.getAccessToken());
//        return createService(serviceClass, authToken);
//    }

    /**
     * If you’re using a custom OkHttpClient, you need to set the client within the Retrofit.Builder
     * by using the .client() method. This will update the default client with the enhanced self-made version.
     */
    private Retrofit getCustomedRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "auth-token")
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your.api.url/v2/")
                .client(client)
                .build();

        return  retrofit;
    }


    // more methods
    // ...
}
