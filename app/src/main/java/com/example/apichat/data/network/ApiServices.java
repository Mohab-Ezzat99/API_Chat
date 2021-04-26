package com.example.apichat.data.network;

import com.example.apichat.data.pojo.ScreenOne;
import com.example.apichat.data.pojo.ScreenTwo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("Screen_1.json")
    Call<ScreenOne> getData();

    @GET ("Screen_2.json")
    Call<ScreenTwo> getData2();
}
