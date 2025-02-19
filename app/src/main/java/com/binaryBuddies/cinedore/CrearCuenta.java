package com.binaryBuddies.cinedore;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.binaryBuddies.cinedore.databinding.ActivityCrearCuentaBinding;
import com.binaryBuddies.cinedore.models.AuthResponse;
import com.binaryBuddies.cinedore.models.RegisterRequest;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.AuthApiService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CrearCuenta extends AppCompatActivity {

    private ActivityCrearCuentaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        hideSystemBars();

        binding = ActivityCrearCuentaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconoFlechaRegresar.setOnClickListener(view -> launchRegistro());
        binding.crearCuenta.setOnClickListener(view -> registerUser());
        binding.yaTienesCuenta.setOnClickListener(view -> launchIniciaSesion());


        binding.inputFechaNacimiento.getEditText().setFocusable(false); // Bloquea la entrada manual
        binding.inputFechaNacimiento.getEditText().setOnClickListener(view -> mostrarDatePicker());
    }

    private void mostrarDatePicker() {
        // Obtener la fecha actual
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    // Formatear la fecha a YYYY-MM-DD
                    String fechaSeleccionada = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    binding.inputFechaNacimiento.getEditText().setText(fechaSeleccionada);
                }, año, mes, dia);

        datePickerDialog.show();
    }

    public void registerUser() {
        String nombre = binding.inputNombre.getEditText().getText().toString().trim();
        String apellidos = binding.inputApellidos.getEditText().getText().toString().trim();
        String correoElectronico = binding.inputCorreo.getEditText().getText().toString().trim();
        String contrasenia = binding.inputContrasena.getEditText().getText().toString().trim();
        String telefono = binding.inputTelefono.getEditText().getText().toString().trim();
        String identificacion = binding.inputDni.getEditText().getText().toString().trim();
        String fechaNacimiento = binding.inputFechaNacimiento.getEditText().getText().toString().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || correoElectronico.isEmpty() || contrasenia.isEmpty() ||
                telefono.isEmpty() || identificacion.isEmpty() || fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthApiService authService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
        RegisterRequest request = new RegisterRequest(nombre, apellidos, correoElectronico, contrasenia, telefono, identificacion, fechaNacimiento);

        Call<AuthResponse> call = authService.register(request);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    saveToken(token);
                    launchRegistro();
                    Toast.makeText(CrearCuenta.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CrearCuenta.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(CrearCuenta.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("cineDorePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", token);
        editor.apply();
    }

    public void launchRegistro() {
        Intent intent = new Intent(CrearCuenta.this, Bienvenida.class);
        startActivity(intent);
    }

    public void launchIniciaSesion() {
        Intent intent = new Intent(CrearCuenta.this, IniciarSesion.class);
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