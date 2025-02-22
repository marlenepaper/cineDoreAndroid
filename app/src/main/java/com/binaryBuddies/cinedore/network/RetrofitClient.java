package com.binaryBuddies.cinedore.network;

import com.binaryBuddies.cinedore.utils.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.1.193:8080/api/";
    private static final String School_URL = "http://192.168.73.240:8080/api/";
    private static final String ANDROID_URL = "http://10.0.2.2:8080/api/";
    private static final String MOBIL = "http://192.168.203.43:8080/api/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .baseUrl(MOBIL)
//                    .baseUrl(ANDROID_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}


