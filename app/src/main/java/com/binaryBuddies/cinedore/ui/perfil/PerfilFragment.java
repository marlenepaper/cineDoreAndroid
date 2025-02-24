package com.binaryBuddies.cinedore.ui.perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.binaryBuddies.cinedore.Bienvenida;
import com.binaryBuddies.cinedore.CrearCuenta;
import com.binaryBuddies.cinedore.IniciarSesion;
import com.binaryBuddies.cinedore.databinding.FragmentPerfilBinding;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private AuthApiService authApiService;
    private boolean isLoggedIn;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString("nombre", "inicia sesión!"); // "Usuario" es el valor por defecto
        String token = sharedPreferences.getString("authToken", "");

        isLoggedIn = (token != null && !token.isEmpty());

        configurarInterfaz();

        if (!nombreUsuario.equals("inicia sesión!")) {
            nombreUsuario = capitalizeFirstLetter(nombreUsuario);
        }

        // Actualizar el TextView con el nombre del usuario
        binding.nombreUsuario.setText(nombreUsuario);

        // Inicializar Retrofit
        authApiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);

        return root;
    }

    private void configurarInterfaz() {
        if (isLoggedIn) {
            // Usuario autenticado, mostrar botones de "Cerrar sesión" y "Eliminar cuenta"
            binding.btnCerrarSesion.setVisibility(View.VISIBLE);
            binding.eliminarCuenta.setVisibility(View.VISIBLE);

            binding.btnIniciarSesion.setVisibility(View.GONE);
            binding.btnCrearCuenta.setVisibility(View.GONE);

            binding.btnCerrarSesion.setOnClickListener(view -> logout());
            binding.eliminarCuenta.setOnClickListener(view -> eliminarCuenta());
        } else {
            // Usuario NO autenticado, mostrar botones de "Iniciar sesión" y "Crear cuenta"
            binding.btnCerrarSesion.setVisibility(View.GONE);
            binding.eliminarCuenta.setVisibility(View.GONE);

            binding.btnIniciarSesion.setVisibility(View.VISIBLE);
            binding.btnCrearCuenta.setVisibility(View.VISIBLE);

            binding.btnIniciarSesion.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), IniciarSesion.class);
                startActivity(intent);
            });

            binding.btnCrearCuenta.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), CrearCuenta.class);
                startActivity(intent);
            });
        }
    }

    private void logout() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("authToken", ""); // Recuperar token

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No hay sesión activa", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("PerfilFragment", "Token enviado en logout: " + token);

        authApiService.logout("Bearer " + token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear(); // Eliminar todos los datos de sesión
                    editor.apply();

                    Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();

                    // Redirigir a la pantalla de bienvenida
                    Intent intent = new Intent(getActivity(), Bienvenida.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Error al cerrar sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eliminarCuenta() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("authToken", ""); // Recuperar token

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No hay sesión activa", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("PerfilFragment", "Token enviado en eliminar cuenta: " + token);

        authApiService.deleteAccount("Bearer " + token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear(); // Eliminar todos los datos de usuario
                    editor.apply();

                    Toast.makeText(getContext(), "Cuenta eliminada", Toast.LENGTH_SHORT).show();

                    // Redirigir a la pantalla de bienvenida
                    Intent intent = new Intent(getActivity(), Bienvenida.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Error al eliminar cuenta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}