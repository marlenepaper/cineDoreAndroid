package com.binaryBuddies.cinedore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.binaryBuddies.cinedore.databinding.ActivityNavegationBarBinding;
import com.binaryBuddies.cinedore.ui.peliculas.PeliculasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class NavegationBar extends AppCompatActivity {

    private ActivityNavegationBarBinding binding;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityNavegationBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageButton logo_cinedore= findViewById(R.id.logo_cinedore);

        logo_cinedore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPeliculas();
            }
        });

        navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.icon_peliculas,
                R.id.icon_pin, R.id.icon_qr, R.id.icon_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navegation_bar);

        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void launchPeliculas() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navegation_bar);
        navController.navigate(R.id.icon_peliculas);
        navView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);

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