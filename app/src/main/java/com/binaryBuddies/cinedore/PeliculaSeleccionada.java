package com.binaryBuddies.cinedore;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewpager.widget.ViewPager;
import com.binaryBuddies.cinedore.adapters.PeliculaSeleccionadaAdapter;
import com.binaryBuddies.cinedore.databinding.ActivityPeliculaSeleccionadaBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

public class PeliculaSeleccionada extends AppCompatActivity {

    private ActivityPeliculaSeleccionadaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityPeliculaSeleccionadaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener los datos del intent
        String titulo = getIntent().getStringExtra("nombre");
        int anio = getIntent().getIntExtra("anio", 0);
        int duracion = getIntent().getIntExtra("duracion", 0);
        String imagenPoster = getIntent().getStringExtra("imagenPoster");
        String categoria = getIntent().getStringExtra("categoria");
        Log.d("DEBUG_CATEGORIA", "Categoría recibida: " + categoria);

        String clasificacion = getIntent().getStringExtra("clasificacion");
        String lenguaje = getIntent().getStringExtra("lenguaje");
        String color = getIntent().getStringExtra("color");
        String formato = getIntent().getStringExtra("formato"); // Se agregó la obtención del formato

        // Manejo de valores nulos
        categoria = (categoria != null) ? categoria : "No disponible";
        clasificacion = (clasificacion != null) ? clasificacion : "No disponible";
        lenguaje = (lenguaje != null) ? lenguaje : "No disponible";
        color = (color != null) ? color : "No disponible";
        formato = (formato != null) ? formato : "No disponible"; // Se agregó manejo de null para formato

        // Asignar datos a la UI
        binding.nombre.setText(titulo);
        binding.anio.setText(String.valueOf(anio));
        binding.duracion.setText(duracion + " min");
        binding.categoria.setText(categoria);
        binding.clasificacion.setText(clasificacion);
        binding.lenguaje.setText(lenguaje);


        // Cargar imagen con Glide
        Glide.with(this)
                .load(imagenPoster)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder_image) // Imagen de carga
                        .error(R.drawable.error_image)) // Imagen si hay error
                .into(binding.imagenPoster);

        PeliculaSeleccionadaAdapter peliculaSeleccionadaAdapter = new PeliculaSeleccionadaAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(peliculaSeleccionadaAdapter);

        // Configurar TabLayout con ViewPager
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.setupWithViewPager(viewPager);

        // Establecer títulos de las pestañas
        tabLayout.getTabAt(0).setText("Horarios");
        tabLayout.getTabAt(1).setText("Sinopsis");

        binding.iconoFlechaRegresar.setOnClickListener(view -> finish());
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