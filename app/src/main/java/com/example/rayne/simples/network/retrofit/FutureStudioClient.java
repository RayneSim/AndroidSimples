package com.example.rayne.simples.network.retrofit;

import com.example.rayne.simples.network.retrofit.data.FutureStudioRssFeed;
import com.example.rayne.simples.network.retrofit.data.Tutorial;
import com.example.rayne.simples.network.retrofit.data.UserInfo;

import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by RayneSim on 17/07/30.
 */

public interface FutureStudioClient {

    @GET("/user/info")
    Call<UserInfo> getUserInfo();

    @PUT("/user/info")
    Call<UserInfo> updateUserInfo(
            @Body UserInfo userInfo
    );

    /**
     * If you don't care at all what the server responds, you can use Void.
     * @return
     */
    @DELETE("/user")
    Call<Void> deleteUser();

    /**
     * Example for passing a full URL
     * @return
     */
    @GET("https://futurestud.io/tutorials/rss/")
    Call<FutureStudioRssFeed> getRssFeed();

    /**
     * If you want the raw response, you can use ResponseBody instead of a specific class like UserInfo.
     * @param profilePhotoUrl
     * @return
     */
    @GET
    Call<ResponseBody> getUserProfilePhoto(
            @Url String profilePhotoUrl
    );

    /**
     * This method getTutorials() method can be replaced by the below method getTutorials()
     * by passing a null value as the value for the last three parameters.
     * @param page
     * @return
     */
    @GET("/tutorials")
    Call<List<Tutorial>> getTutorials(
            @Query("page") Integer page
    );

    @GET("/tutorials")
    Call<List<Tutorial>> getTutorials(
            @Query("page") Integer page,
            @Query("order") String order,
            @Query("author") String author,
            @Query("published_at") Date date
    );

}
