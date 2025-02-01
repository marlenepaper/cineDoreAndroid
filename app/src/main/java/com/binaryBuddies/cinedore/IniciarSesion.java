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

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();


        setContentView(R.layout.activity_iniciar_sesion);

        ImageButton arrowback= findViewById(R.id.icono_flecha_regresar);
        TextView btnIniciarSesion = findViewById(R.id.iniciar_sesion);
        TextView txtInvitado = findViewById(R.id.ya_tienes_cuenta);

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegistro();
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchIniciaSesion();
            }
        });

        txtInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCreaTuCuenta();
            }
        });
    }

    public void launchRegistro() {
        Intent intent = new Intent(IniciarSesion.this, Bienvenida.class);
        startActivity(intent);
    }

    public void launchIniciaSesion() {
        Intent intent = new Intent(IniciarSesion.this, NavegationBar.class);
        startActivity(intent);
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