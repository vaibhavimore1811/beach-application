package com.sanika.beachapplication.api;

import com.sanika.beachapplication.model.Beach;
import com.sanika.beachapplication.model.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search.php")
    Call<List<Beach>> getNearbyBeaches(
            @Query("q") String query,
            @Query("format") String format,
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("radius") int radius
    );

    @GET
    Call<WeatherResponse> getWeather(@Url String url);
}
