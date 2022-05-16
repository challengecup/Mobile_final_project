package com.example.primevideoclone.retrofit;

import com.example.primevideoclone.model.BannerMovies;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("banner")
    Observable<List<BannerMovies>> getAllBanners();
}
