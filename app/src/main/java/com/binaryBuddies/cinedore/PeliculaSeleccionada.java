package com.binaryBuddies.cinedore;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewpager.widget.ViewPager;
import com.binaryBuddies.cinedore.adapters.PeliculaSeleccionadaAdapter;
import com.binaryBuddies.cinedore.databinding.ActivityPeliculaSeleccionadaBinding;
import com.binaryBuddies.cinedore.ui.peliculaSeleccionada.HorariosFragment;
import com.binaryBuddies.cinedore.ui.peliculaSeleccionada.SinopsisFragment;
import com.bumptech.glide.Glide;
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
        String anio = getIntent().getStringExtra("anio");
        String duracion = getIntent().getStringExtra("duracion");
        String imagenPoster = getIntent().getStringExtra("imagenPoster");
        String categoria = getIntent().getStringExtra("categoria");
        String clasificacion = getIntent().getStringExtra("clasificacion");
        String lenguaje = getIntent().getStringExtra("lenguaje");
        String sinopsis = getIntent().getStringExtra("sinopsis");
        String formato = getIntent().getStringExtra("formato");

        String funcionesTexto = getIntent().getStringExtra("funciones");

        // Asignar datos a los elementos de la vista
        binding.nombre.setText(titulo);
        binding.anio.setText(anio);
        binding.duracion.setText(duracion);
//        binding.sinopsis.setText(sinopsis);
        binding.categoria.setText(categoria);
        binding.clasificacion.setText(clasificacion);
//        binding.formato.setText(formato);
        binding.lenguaje.setText(lenguaje);

        HorariosFragment horariosFragment = new HorariosFragment();
        Bundle bundle = new Bundle();
        bundle.putString("funciones", funcionesTexto);
        horariosFragment.setArguments(bundle);

//        SinopsisFragment sinopsisFragment = new SinopsisFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("funciones", funcionesTexto);
//        sinopsisFragment.setArguments(bundle);

        // Cargar imagen con Glide
        Glide.with(this).load(imagenPoster).into(binding.imagenPoster);

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

