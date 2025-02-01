package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();
        setContentView(R.layout.activity_bienvenida);

        TextView btnIniciarSesion = findViewById(R.id.iniciar_sesion);
        TextView btnCrearCuenta = findViewById(R.id.crea_tu_cuenta_titulo);
        TextView txtInvitado = findViewById(R.id.continua_como_invitado);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchIniciaSesion();
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCrearTuCuenta();
            }
        });

        txtInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPeliculas();
            }
        });

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