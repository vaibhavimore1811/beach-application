package com.sanika.beachapplication.api;

import com.sanika.beachapplication.model.Beach;
import com.sanika.beachapplication.model.GeoapifyHotelResponse;
import com.sanika.beachapplication.model.HotelDetailResponse;
import com.sanika.beachapplication.model.HotelResponse;
import com.sanika.beachapplication.model.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// ApiInterface.java
import com.sanika.beachapplication.model.BeachResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("v2/places")
    Call<BeachResponse> getNearbyBeaches(
            @Query("categories") String categories,
            @Query("filter") String filter,
            @Query("bias") String bias,
            @Query("limit") int limit,
            @Query("apiKey") String apiKey
    );

    @GET
    Call<HotelResponse> getNearbyHotels(@Url String url);

    @GET
    Call<WeatherResponse> getWeatherData(@Url String url);

        @GET("nearbysearch/json")
        Call<HotelResponse> getNearbyHotels(
                @Query("location") String location,
                @Query("radius") int radius,
                @Query("type") String type,
                @Query("key") String apiKey
        );

    @GET("place/nearbysearch/json")
    Call<HotelResponse> getNearbyHotels(@Query("location") String location,
                                        @Query("radius") String radius,
                                        @Query("type") String type,
                                        @Query("key") String apiKey);

    @GET("place/details/json")
    Call<HotelDetailResponse> getHotelDetails(@Query("placeid") String placeId,
                                              @Query("key") String apiKey);

    @GET("v1/place/nearby")
    Call<GeoapifyHotelResponse> getNearbyHotels(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("apiKey") String apiKey);
    }

