package com.binaryBuddies.cinedore.services;

import com.binaryBuddies.cinedore.models.PeliculaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculasApiService {

    @GET("peliculas") // Endpoint que devuelve todas las películas
    Call<List<PeliculaModel>> getPeliculas();

    @GET("peliculas/{id}") // Endpoint para obtener una película específica
    Call<PeliculaModel> getPeliculaById(@Path("id") long id);
}
