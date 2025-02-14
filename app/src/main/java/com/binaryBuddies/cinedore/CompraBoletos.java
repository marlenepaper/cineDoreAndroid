package com.binaryBuddies.cinedore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.bumptech.glide.Glide;

public class CompraBoletos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();
        setContentView(R.layout.activity_compra_boletos);

        String nombre = getIntent().getStringExtra("nombre");
        String anio = getIntent().getStringExtra("anio");
        String duracion = getIntent().getStringExtra("duracion");
        String sinopsis = getIntent().getStringExtra("sinopsis");
        String imagenPoster = getIntent().getStringExtra("imagenPoster");
        String fechaFuncion = getIntent().getStringExtra("fecha_funcion");
        String salaFuncion = getIntent().getStringExtra("sala_funcion");

//        ImageView imgPoster = findViewById(R.id.imagen_poster);
//        TextView tvTitulo = findViewById(R.id.titulo);
//        TextView tvSinopsis = findViewById(R.id.sinopsis);
//        TextView tvFecha = findViewById(R.id.fecha_funcion);
//        TextView tvSala = findViewById(R.id.sala_funcion);

//        Glide.with(this).load(imagenPoster).into(imgPoster);
//        tvTitulo.setText(nombre + " (" + anio + ")");
//        tvSinopsis.setText(sinopsis);
//        tvFecha.setText("Fecha: " + fechaFuncion);
//        tvSala.setText("Sala: " + salaFuncion);

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