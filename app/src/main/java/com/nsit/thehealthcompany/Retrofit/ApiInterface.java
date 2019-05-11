package com.nsit.thehealthcompany.Retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("user/get/")
    Call<JsonArray> loginUser(@QueryMap(encoded = true) Map<String, String> data);

    @POST("user/create/")
    Call<JsonObject> signUpUser(@Body JsonObject dataObject);

    @POST("user/core/create/")
    Call<JsonObject> signUpWithBasicInfo(@Body JsonObject userInfoObject);

    @POST("user/all/get")
    Call<JsonArray> getUserCalorieData(@QueryMap(encoded = true) Map<String, String> email);

    @GET("blog/get")
    Call<JsonArray> getBlogPosts();

}
