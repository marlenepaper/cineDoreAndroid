package com.binaryBuddies.cinedore.services;

import com.binaryBuddies.cinedore.models.PeliculaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculasApiService {

    @GET("peliculasDTO")
    Call<List<PeliculaModel>> getPeliculas();

    @GET("peliculas/{id}")
    Call<PeliculaModel> getPeliculaById(@Path("id") long id);
}
