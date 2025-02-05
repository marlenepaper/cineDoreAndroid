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

import com.binaryBuddies.cinedore.databinding.ActivityIniciarSesionBinding;

public class IniciarSesion extends AppCompatActivity {

    private ActivityIniciarSesionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();


        binding = ActivityIniciarSesionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconoFlechaRegresar.setOnClickListener(view -> launchRegistro());
        binding.iniciarSesion.setOnClickListener(view -> launchIniciaSesion());
        binding.yaTienesCuenta.setOnClickListener(view -> launchCreaTuCuenta());
    }

    public void launchRegistro() {
        Intent intent = new Intent(IniciarSesion.this, Bienvenida.class);
        startActivity(intent);
    }

    public void launchIniciaSesion() {
        Intent intent = new Intent(IniciarSesion.this, NavegationBar.class);
        startActivity(intent);
        finishAffinity(); // Cierra todas las actividades previas
    }


    public void launchCreaTuCuenta() {
        Intent intent = new Intent(IniciarSesion.this, CrearCuenta.class);
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