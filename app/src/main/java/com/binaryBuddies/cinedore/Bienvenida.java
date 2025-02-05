package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivityBienvenidaBinding;
import com.binaryBuddies.cinedore.ui.peliculas.PeliculasFragment;

public class Bienvenida extends AppCompatActivity {

    private ActivityBienvenidaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityBienvenidaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // listeners con lambdas
        binding.iniciarSesion.setOnClickListener(view -> launchIniciaSesion());
        binding.creaTuCuentaTitulo.setOnClickListener(view -> launchCrearTuCuenta());
        binding.continuaComoInvitado.setOnClickListener(view -> launchPeliculas());

    }

    public void launchIniciaSesion() {
        Intent intent = new Intent(Bienvenida.this, IniciarSesion.class);
        startActivity(intent);
    }
    public void launchCrearTuCuenta() {
        Intent intent = new Intent(Bienvenida.this, CrearCuenta.class);
        startActivity(intent);
    }

    public void launchPeliculas() {
        Intent intent = new Intent(Bienvenida.this, NavegationBar.class);
        startActivity(intent);
        finish();
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