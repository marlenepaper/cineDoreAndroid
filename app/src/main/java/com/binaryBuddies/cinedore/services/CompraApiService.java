package com.binaryBuddies.cinedore.services;
import com.binaryBuddies.cinedore.models.CompraDTO;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface CompraApiService {
    @POST("compras/crear")
    Call<CompraDTO> crearCompra(@Body CompraDTO compra);
}

