package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.binaryBuddies.cinedore.adapters.PeliculaSeleccionadaAdapter;
import com.binaryBuddies.cinedore.databinding.ActivitySeleccionBoletosBinding;
import com.binaryBuddies.cinedore.models.CompraModel;
import com.binaryBuddies.cinedore.models.TicketEntradaModel;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.CompraApiService;
import com.binaryBuddies.cinedore.ui.ticket.TicketFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

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

        // Obtener los datos del intent
        String titulo = getIntent().getStringExtra("nombre");
        String imagenPoster = getIntent().getStringExtra("imagenPoster");
        String funcion = getIntent().getStringExtra("fecha_funcion");
        String sala = getIntent().getStringExtra("sala_funcion");
        int duracion = getIntent().getIntExtra("duracion", 0);
        String lenguaje = getIntent().getStringExtra("lenguaje");
        String clasificacion = getIntent().getStringExtra("clasificacion");

        String fecha = funcion;
        String hora = "";
        if (funcion != null && funcion.contains("T")) { // Formato ISO-8601
            String[] fechaHora = funcion.split("T");
            fecha = formatearFecha(fechaHora[0]); // yyyy-MM-dd → dd/MM/yyyy
            hora = fechaHora[1].substring(0, 5); // HH:mm:ss → HH:mm
        }

        // Asignar datos a los elementos de la vista
        binding.movieTitle.setText(titulo);
        binding.movieDate.setText(fecha);
        binding.movieTime.setText(hora);
        binding.movieRoomNum.setText(sala);
        binding.movieDuration.setText(String.format(Locale.getDefault(), "%d min", duracion));
        binding.movieLanguage.setText(lenguaje);
        binding.movieClassification.setText(clasificacion);
        Glide.with(this).load(imagenPoster).into(binding.moviePoster);

        binding.tvTotal.setText(String.format(Locale.getDefault(), "0,00 €"));

        // Manejo de botones de selección
        configurarBotones();

        // Botón de compra
        binding.btnComprar.setOnClickListener(view -> realizarCompra());

        // Botón de cancelar
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
        // Crear instancia de Retrofit
        CompraApiService apiService = RetrofitClient.getRetrofitInstance().create(CompraApiService.class);

        // Obtener datos de la función
        Long usuarioId = 1L; // Se debería obtener de sesión o SharedPreferences
        Long funcionId = getIntent().getLongExtra("funcion_id", 0);

        // Crear lista de tickets
        List<TicketEntradaModel> tickets = new ArrayList<>();
        int totalBoletos = cantidadGeneral + cantidadReducida + cantidadGratis;
        for (int i = 0; i < totalBoletos; i++) {
            tickets.add(new TicketEntradaModel("QR-" + System.currentTimeMillis(), 1L)); // Estado 1L = Activo
        }

        // Crear objeto CompraModel
        CompraModel compra = new CompraModel(usuarioId, funcionId, new BigDecimal(totalBoletos * PRECIO_GENERAL), tickets);

        // Enviar compra al backend
        Call<CompraModel> call = apiService.crearCompra(compra);
        call.enqueue(new retrofit2.Callback<CompraModel>() {
            @Override
            public void onResponse(Call<CompraModel> call, retrofit2.Response<CompraModel> response) {
                if (response.isSuccessful()) {
                    Log.d("Compra", "Compra realizada con éxito");
                    irATicketFragment();
                } else {
                    Log.e("Compra", "Error en la respuesta del servidor: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CompraModel> call, Throwable t) {
                Log.e("Compra", "Error al conectar con el servidor: " + t.getMessage());
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
