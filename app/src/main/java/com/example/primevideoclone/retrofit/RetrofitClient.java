package com.example.primevideoclone.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public  static final String BASE_URL = "https://627ef705271f386ceffea834.mockapi.io/mockapimobile/api/";

    public static ApiInterface getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BASE_URL)
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        return api;
    }

}
