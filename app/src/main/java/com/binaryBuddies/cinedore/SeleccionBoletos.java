package com.binaryBuddies.cinedore;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewpager.widget.ViewPager;

import com.binaryBuddies.cinedore.adapters.PeliculaSeleccionadaAdapter;
import com.binaryBuddies.cinedore.databinding.ActivitySeleccionBoletosBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class SeleccionBoletos extends AppCompatActivity {
    private ActivitySeleccionBoletosBinding binding;

    private int cantidadGeneral = 0;
    private int cantidadReducida = 0;
    private int cantidadGratis = 0;

    private static final int PRECIO_GENERAL = 3;
    private static final int PRECIO_REDUCIDA = 2;
    private static final int PRECIO_GRATIS = 0;

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

        // Asignar datos a los elementos de la vista
        binding.movieTitle.setText(titulo);
        Glide.with(this).load(imagenPoster).into(binding.moviePoster);

        // Manejo de botones de selección
        configurarBotones();

        // Botón de compra
        binding.btnComprar.setOnClickListener(view -> realizarCompra());

        // Botón de cancelar
        binding.btnCancelar.setOnClickListener(view -> finish());
    }

    private void configurarBotones() {
        // Entradas generales
        binding.btnMenosGeneral.setOnClickListener(view -> actualizarCantidad("general", -1));
        binding.btnMasGeneral.setOnClickListener(view -> actualizarCantidad("general", 1));

        // Entradas reducidas
        binding.btnMenosReducida.setOnClickListener(view -> actualizarCantidad("reducida", -1));
        binding.btnMasReducida.setOnClickListener(view -> actualizarCantidad("reducida", 1));

        // Entradas gratuitas
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
        int total = (cantidadGeneral * PRECIO_GENERAL) + (cantidadReducida * PRECIO_REDUCIDA);
        binding.tvTotal.setText(String.format("%.2f €", (float) total));
    }

    private void realizarCompra() {
        // Aquí podrías abrir otra actividad para el pago o confirmación de la compra
        // Intent intent = new Intent(this, PagoActivity.class);
        // startActivity(intent);
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());

        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
    }
}
