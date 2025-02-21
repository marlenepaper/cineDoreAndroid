package com.binaryBuddies.cinedore.services;
import com.binaryBuddies.cinedore.models.CompraModel;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface CompraApiService {
    @POST("compras/crear")
    Call<CompraModel> crearCompra(@Body CompraModel compra);
}

