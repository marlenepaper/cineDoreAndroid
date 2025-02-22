package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivitySeleccionBoletosBinding;
import com.binaryBuddies.cinedore.models.CompraDTO;
import com.binaryBuddies.cinedore.models.TicketEntradaDTO;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.CompraApiService;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

public class SeleccionBoletos extends AppCompatActivity {
    private ActivitySeleccionBoletosBinding binding;

    private int cantidadGeneral = 0;
    private int cantidadReducida = 0;
    private int cantidadGratis = 0;

    private static final int PRECIO_GENERAL = 3;
    private static final int PRECIO_REDUCIDA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivitySeleccionBoletosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener datos del Intent
        String titulo = getIntent().getStringExtra("nombre");
        String imagenPoster = getIntent().getStringExtra("imagenPoster");
        String funcion = getIntent().getStringExtra("fecha_funcion");
        String sala = getIntent().getStringExtra("sala_funcion");
        int duracion = getIntent().getIntExtra("duracion", 0);
        String lenguaje = getIntent().getStringExtra("lenguaje");
        String clasificacion = getIntent().getStringExtra("clasificacion");

        String fecha = funcion;
        String hora = "";
        if (funcion != null && funcion.contains("T")) {
            String[] fechaHora = funcion.split("T");
            fecha = formatearFecha(fechaHora[0]);
            hora = fechaHora[1].substring(0, 5);
        }

        binding.movieTitle.setText(titulo);
        binding.movieDate.setText(fecha);
        binding.movieTime.setText(hora);
        binding.movieRoomNum.setText(sala);
        binding.movieDuration.setText(String.format(Locale.getDefault(), "%d min", duracion));
        binding.movieLanguage.setText(lenguaje);
        binding.movieClassification.setText(clasificacion);
        Glide.with(this).load(imagenPoster).into(binding.moviePoster);

        binding.tvTotal.setText(String.format(Locale.getDefault(), "0,00 €"));

        configurarBotones();

        binding.btnComprar.setOnClickListener(view -> realizarCompra());
        binding.btnCancelar.setOnClickListener(view -> finish());
        binding.iconoFlechaRegresar.setOnClickListener(view -> finish());
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

    private void configurarBotones() {
        binding.btnMenosGeneral.setOnClickListener(view -> actualizarCantidad("general", -1));
        binding.btnMasGeneral.setOnClickListener(view -> actualizarCantidad("general", 1));

        binding.btnMenosReducida.setOnClickListener(view -> actualizarCantidad("reducida", -1));
        binding.btnMasReducida.setOnClickListener(view -> actualizarCantidad("reducida", 1));

        binding.btnMenosGratis.setOnClickListener(view -> actualizarCantidad("gratis", -1));
        binding.btnMasGratis.setOnClickListener(view -> actualizarCantidad("gratis", 1));
    }

    private void actualizarCantidad(String tipo, int cambio) {
        switch (tipo) {
            case "general":
                cantidadGeneral = Math.max(0, cantidadGeneral + cambio);
                binding.tvCantidadGeneral.setText(String.valueOf(cantidadGeneral));
                break;
            case "reducida":
                cantidadReducida = Math.max(0, cantidadReducida + cambio);
                binding.tvCantidadReducida.setText(String.valueOf(cantidadReducida));
                break;
            case "gratis":
                cantidadGratis = Math.max(0, cantidadGratis + cambio);
                binding.tvCantidadGratis.setText(String.valueOf(cantidadGratis));
                break;
        }
        actualizarTotal();
    }

    private void actualizarTotal() {
        int totalBoletos = cantidadGeneral + cantidadReducida + cantidadGratis;
        int totalPrecio = (cantidadGeneral * PRECIO_GENERAL) + (cantidadReducida * PRECIO_REDUCIDA);
        binding.totalCantidad.setText(String.format("x%d", totalBoletos));
        binding.tvTotal.setText(String.format(Locale.getDefault(), "%.2f €", (float) totalPrecio));
    }

    private void realizarCompra() {
        int totalTickets = cantidadGeneral + cantidadReducida + cantidadGratis;
        if (totalTickets <= 0) {
            Log.e("Compra", "No se ha seleccionado ningún boleto.");
            return;
        }

        // Se genera un único ticket para la compra
        String codigoQr = "TICKET-" + java.util.UUID.randomUUID().toString();
        TicketEntradaDTO ticket = new TicketEntradaDTO(codigoQr, 1L); // Estado activo (1L)

        // Calcular el total a pagar (solo se cobran los boletos generales y reducidos)
        BigDecimal totalPago = BigDecimal.valueOf((cantidadGeneral * PRECIO_GENERAL) + (cantidadReducida * PRECIO_REDUCIDA));

        // Obtener usuarioId y funcionId (asegúrate de enviarlos en el intent o de obtenerlos desde otra fuente)
        Long usuarioId = getIntent().getLongExtra("usuarioId", 1L);
        Long funcionId = getIntent().getLongExtra("funcionId", 0L);

        // Crear CompraDTO con un único ticket
        List<TicketEntradaDTO> tickets = new ArrayList<>();
        tickets.add(ticket);

        CompraDTO compraDTO = new CompraDTO(usuarioId, funcionId, totalPago, tickets);

        // Llamada a la API mediante Retrofit
        CompraApiService apiService = RetrofitClient.getRetrofitInstance().create(CompraApiService.class);
        Call<CompraDTO> call = apiService.crearCompra(compraDTO);
        call.enqueue(new retrofit2.Callback<CompraDTO>() {
            @Override
            public void onResponse(Call<CompraDTO> call, retrofit2.Response<CompraDTO> response) {
                if (response.isSuccessful()) {
                    irATicketFragment();
                } else {
                    Log.e("Compra", "Error en la compra: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CompraDTO> call, Throwable t) {
                Log.e("Compra", "Fallo al realizar la compra", t);
            }
        });
    }

    private void irATicketFragment() {
        Intent intent = new Intent(this, NavegationBar.class);
        intent.putExtra("navigateTo", "ticketFragment");
        startActivity(intent);
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
    }

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
