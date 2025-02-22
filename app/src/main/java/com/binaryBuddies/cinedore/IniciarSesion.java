package com.binaryBuddies.cinedore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivityIniciarSesionBinding;
import com.binaryBuddies.cinedore.models.AuthResponse;
import com.binaryBuddies.cinedore.models.LoginRequest;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IniciarSesion extends AppCompatActivity {

    private ActivityIniciarSesionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityIniciarSesionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconoFlechaRegresar.setOnClickListener(view -> launchRegistro());
        binding.iniciarSesion.setOnClickListener(view -> loginUser());
        binding.yaTienesCuenta.setOnClickListener(view -> launchCreaTuCuenta());
    }

    public void loginUser() {
        String email = binding.inputEmail.getEditText().getText().toString().trim();
        String password = binding.inputPassword.getEditText().getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthApiService authService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
        Call<AuthResponse> call = authService.login(new LoginRequest(email, password));

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    String nombre = response.body().getNombre();
                    Long usuarioId = response.body().getUsuarioId(); // Asegúrate de que esta propiedad se llame así en tu modelo
                    saveUserData(token, nombre, usuarioId);
                    launchPeliculas();
                } else {
                    Log.e("LOGIN_ERROR", "Error en login: " + response.code());
                    Toast.makeText(IniciarSesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("LOGIN_FAILURE", "Fallo la conexión: " + t.getMessage());
                Toast.makeText(IniciarSesion.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserData(String token, String nombre, Long id) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", token);
        editor.putString("nombre", nombre);
        // Usamos la clave "usuarioId" para que sea consistente en toda la app
        editor.putLong("usuarioId", id);
        editor.apply();
    }

    public void launchRegistro() {
        Intent intent = new Intent(IniciarSesion.this, Bienvenida.class);
        startActivity(intent);
    }

    public void launchPeliculas() {
        Intent intent = new Intent(IniciarSesion.this, NavegationBar.class);
        startActivity(intent);
        finishAffinity(); // Cierra todas las actividades previas
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
