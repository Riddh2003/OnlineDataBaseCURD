package com.example.onlinedatabasecurd;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("royal/show.php")
    Call<ResultContent>getcontect();
    @FormUrlEncoded
    @POST("royal/insert.php")
    Call<ResultContent>insert(
      @Field("name")String name,
      @Field("email")String email,
      @Field("password")String password
    );

    @FormUrlEncoded
    @POST("royal/update.php")
    Call<ResultContent>update(
            @Field("name")String name,
            @Field("email")String email,
            @Field("password")String password,
            @Field("id")String id
    );

    @FormUrlEncoded
    @POST("royal/delete.php")
    Call<ResultContent>delete(
            @Field("id")String id
    );
}
