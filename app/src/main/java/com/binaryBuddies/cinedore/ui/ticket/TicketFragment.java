package com.binaryBuddies.cinedore.ui.ticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.databinding.FragmentTicketQrBinding;
import com.binaryBuddies.cinedore.models.TicketDisplayDTO;
import com.binaryBuddies.cinedore.services.TicketApiService;
import com.bumptech.glide.Glide;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment {

    private FragmentTicketQrBinding binding;
    private TicketApiService ticketApiService;
    private long usuarioId = -1; // ID del usuario (se obtiene de SharedPreferences)
    private boolean isLoggedIn;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTicketQrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener el usuario desde SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        usuarioId = sharedPreferences.getLong("usuarioId", -1);
        String token = sharedPreferences.getString("authToken", "");

        isLoggedIn = (token != null && !token.isEmpty() && usuarioId != -1);

        ticketApiService = RetrofitClient.getRetrofitInstance().create(TicketApiService.class);

        // Si está autenticado, cargar tickets; si no, mostrar pantalla por defecto
        if (isLoggedIn) {
            cargarTicket();
        } else {
            mostrarVistaPorDefecto();
        }

        return root;
    }

    private void cargarTicket() {
        ticketApiService.getTicketsByUserId(usuarioId).enqueue(new Callback<List<TicketDisplayDTO>>() {
            @Override
            public void onResponse(Call<List<TicketDisplayDTO>> call, Response<List<TicketDisplayDTO>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    mostrarTicket(response.body().get(0));
                } else {
                    mostrarVistaPorDefecto();
                }
            }

            @Override
            public void onFailure(Call<List<TicketDisplayDTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al cargar los tickets", Toast.LENGTH_SHORT).show();
                mostrarVistaPorDefecto();
            }
        });
    }

    private void mostrarTicket(TicketDisplayDTO ticket) {
        binding.backgroundSinCompras.setVisibility(View.GONE);
        binding.imagenPelicula.setVisibility(View.VISIBLE);
        binding.ticketLayout.setVisibility(View.VISIBLE);

        // Asignar valores del ticket a la vista
        binding.movieTitle.setText(ticket.getTituloPelicula());
        binding.movieClassification.setText(ticket.getClasificacion());
        binding.movieLanguage.setText(ticket.getLenguaje());
        binding.movieDuration.setText(ticket.getDuracion() + " min");
        binding.movieDate.setText(ticket.getFechaFuncion());
        binding.numeroDeEntradas.setText("1 Entrada");


        Glide.with(this)
                .load(ticket.getImagenPelicula())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(binding.moviePoster);

    }

    private void mostrarVistaPorDefecto() {
        binding.backgroundSinCompras.setVisibility(View.VISIBLE);
        binding.imagenPelicula.setVisibility(View.GONE);
        binding.ticketLayout.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
