package com.binaryBuddies.cinedore.services;

import com.binaryBuddies.cinedore.models.AuthResponse;
import com.binaryBuddies.cinedore.models.LoginRequest;
import com.binaryBuddies.cinedore.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface AuthApiService {

    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("auth/logout")
    Call<Void> logout(@retrofit2.http.Header("Authorization") String token);

    @DELETE("auth/delete-account")
    Call<Void> deleteAccount(@retrofit2.http.Header("Authorization") String token);
}

