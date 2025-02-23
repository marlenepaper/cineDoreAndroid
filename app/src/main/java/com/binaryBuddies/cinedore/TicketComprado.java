package com.binaryBuddies.cinedore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class TicketComprado extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle, movieDate, movieTime, movieRoomNum, movieDuration,
            movieLanguage, movieClassification, numeroDeEntradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_comprado);

        // Vincular las vistas con el XML
        moviePoster = findViewById(R.id.moviePoster);
        movieTitle = findViewById(R.id.movie_title);
        movieDate = findViewById(R.id.movie_date);
        movieTime = findViewById(R.id.movie_time);
        movieRoomNum = findViewById(R.id.movie_room_num);
        movieDuration = findViewById(R.id.movie_duration);
        movieLanguage = findViewById(R.id.movie_language);
        movieClassification = findViewById(R.id.movie_classification);
        numeroDeEntradas = findViewById(R.id.numero_de_entradas);

        // Obtener datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titulo = extras.getString("titulo", "Sin título");
            String imagenPoster = extras.getString("imagenPoster", "");
            String fecha = extras.getString("fecha_funcion", "");
            String sala = extras.getString("sala_funcion", "");
            int duracion = extras.getInt("duracion", 0);
            String lenguaje = extras.getString("lenguaje", "");
            String clasificacion = extras.getString("clasificacion", "");
            int numeroBoletos = extras.getInt("numero_boletos", 0);

            // Asignar valores a las vistas
            movieTitle.setText(titulo);
            movieDate.setText(fecha);
            movieRoomNum.setText(sala);
            movieDuration.setText(duracion + " min");
            movieLanguage.setText(lenguaje);
            movieClassification.setText(clasificacion);
            numeroDeEntradas.setText(String.format("Entradas: %d", numeroBoletos));

            Glide.with(this).load(imagenPoster).into(moviePoster);
        }
    }
}
