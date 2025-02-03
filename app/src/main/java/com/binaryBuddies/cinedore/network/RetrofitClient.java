//package com.binaryBuddies.cinedore.network;
//
//public class RetrofitClient {
//    private static Retrofit retrofit;
//    private static final String BASE_URL = "https://api.ejemplo.com/";
//
//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//}