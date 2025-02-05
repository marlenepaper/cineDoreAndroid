package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivityCrearCuentaBinding;

public class CrearCuenta extends AppCompatActivity {

    private ActivityCrearCuentaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityCrearCuentaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconoFlechaRegresar.setOnClickListener(view -> launchRegistro());
        binding.crearCuenta.setOnClickListener(view -> launchRegistro());
        binding.yaTienesCuenta.setOnClickListener(view -> launchIniciaSesion());
    }

    public void launchRegistro() {
        Intent intent = new Intent(CrearCuenta.this, Bienvenida.class);
        startActivity(intent);
    }

    public void launchIniciaSesion() {
        Intent intent = new Intent(CrearCuenta.this, IniciarSesion.class);
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
}