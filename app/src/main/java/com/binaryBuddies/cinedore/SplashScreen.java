package com.binaryBuddies.cinedore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        verificarPrimeraEjecucion();
        launchInicioRegistro();
    }

    private void verificarPrimeraEjecucion() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        boolean primeraVez = sharedPreferences.getBoolean("primera_ejecucion", true);

        if (primeraVez) {
            // Elimina los datos de usuario si es la primera vez después de instalar la app
            SharedPreferences userPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.clear();
            editor.apply();

            // Marcar que ya no es la primera vez
            SharedPreferences.Editor appEditor = sharedPreferences.edit();
            appEditor.putBoolean("primera_ejecucion", false);
            appEditor.apply();
        }
    }

    public void launchInicioRegistro() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("authToken", "");

                Intent intent;
                if (token != null && !token.isEmpty()) {
                    // Usuario tiene sesión activa, dirigir a la actividad principal
                    intent = new Intent(SplashScreen.this, NavegationBar.class);
                } else {
                    // Usuario no autenticado, dirigir a Bienvenida
                    intent = new Intent(SplashScreen.this, Bienvenida.class);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 4000);
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
    }
}