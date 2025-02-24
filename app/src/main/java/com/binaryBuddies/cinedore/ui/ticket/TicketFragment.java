package com.binaryBuddies.cinedore.ui.ticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment {

    private FragmentTicketQrBinding binding;
    private TicketApiService ticketApiService;
    private long usuarioId = -1;
    private boolean isLoggedIn;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTicketQrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        usuarioId = sharedPreferences.getLong("usuarioId", -1);
        String token = sharedPreferences.getString("authToken", "");

        isLoggedIn = (token != null && !token.isEmpty() && usuarioId != -1);

        ticketApiService = RetrofitClient.getRetrofitInstance().create(TicketApiService.class);


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

        String fechaFormateada = ticket.getFechaFuncion();
        String hora = "";

        if (fechaFormateada != null && fechaFormateada.contains("T")) {
            String[] fechaHora = fechaFormateada.split("T");
            fechaFormateada = formatearFecha(fechaHora[0]);
            hora = fechaHora[1].substring(0, 5); // Extrae la hora HH:mm
        }

        // Asignar valores del ticket a la vista
        binding.movieTitle.setText(ticket.getTituloPelicula());
        binding.movieClassification.setText(ticket.getClasificacion());
        binding.movieLanguage.setText(ticket.getLenguaje());
        binding.movieDuration.setText(ticket.getDuracion() + " min");
        binding.movieDate.setText(fechaFormateada); // Fecha formateada
        binding.movieTime.setText(hora); // Hora extraída
        binding.numeroDeEntradas.setText(ticket.getCantidadTickets() + "x Entrada(s)");



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

    private String formatearFecha(String fechaISO) {
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat formatoSalida = new SimpleDateFormat("EEEE dd 'de' MMMM", new Locale("es", "ES"));
            return capitalizeFirstLetter(formatoSalida.format(formatoEntrada.parse(fechaISO)));
        } catch (Exception e) {
            return fechaISO;
        }
    }

    /**
     * Capitaliza la primera letra de una cadena.
     */
    private String capitalizeFirstLetter(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}

