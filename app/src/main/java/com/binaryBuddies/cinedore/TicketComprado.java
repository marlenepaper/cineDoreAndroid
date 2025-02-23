package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.binaryBuddies.cinedore.models.TicketDisplayDTO;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.Locale;

public class TicketComprado extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle, movieDate, movieTime, movieRoomNum, movieDuration,
            movieLanguage, movieClassification, numeroDeEntradas, btnFinalizar;

    private String titulo, imagenPoster, fecha, sala, lenguaje, clasificacion, codigoQr;
    private int duracion, numeroBoletos;
    private Long funcionId;
    private BigDecimal totalPago;

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
        btnFinalizar = findViewById(R.id.btnFinalizar);

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


            String fechaf = fecha;
            String hora = "";
            if (fecha != null && fecha.contains("T")) {
                String[] fechaHora = fecha.split("T");
                fechaf = formatearFecha(fechaHora[0]);
                hora = fechaHora[1].substring(0, 5);
            }


            // Asignar valores a las vistas
            movieTitle.setText(titulo);
            movieDate.setText(fechaf);
            movieTime.setText(hora);
            movieRoomNum.setText(sala);
            movieDuration.setText(duracion + " min");
            movieLanguage.setText(lenguaje);
            movieClassification.setText(clasificacion);
            numeroDeEntradas.setText(String.format("%dx Entrada(s)", numeroBoletos));

            Glide.with(this).load(imagenPoster).into(moviePoster);
        }
        btnFinalizar.setOnClickListener(view -> finalizarCompra());
    }

    private void finalizarCompra() {
        // Crear el objeto TicketDisplayDTO
        TicketDisplayDTO ticketDisplayDTO = new TicketDisplayDTO(
                funcionId,
                totalPago,
                codigoQr,
                fecha,
                titulo,
                imagenPoster,
                clasificacion,
                lenguaje,
                duracion
        );

        // Navegar a NavigationBar y pasar el TicketDisplayDTO
        Intent intent = new Intent(this, NavegationBar.class);
        intent.putExtra("ticketDisplay", ticketDisplayDTO);
        startActivity(intent);
        finish(); // Cerrar TicketComprado para no volver atrás
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

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
