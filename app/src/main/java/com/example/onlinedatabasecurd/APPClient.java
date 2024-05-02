package com.example.onlinedatabasecurd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APPClient {
    public static final String BASEURL="http://192.168.29.162/";
    public static Retrofit getclient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
