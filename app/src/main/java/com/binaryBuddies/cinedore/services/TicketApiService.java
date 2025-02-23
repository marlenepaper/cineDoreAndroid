package com.binaryBuddies.cinedore.services;

import com.binaryBuddies.cinedore.models.TicketDisplayDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TicketApiService {
    @GET("compraTicket/usuario/{usuarioId}")
    Call<List<TicketDisplayDTO>> getTicketsByUserId(@Path("usuarioId") long usuarioId);
}
