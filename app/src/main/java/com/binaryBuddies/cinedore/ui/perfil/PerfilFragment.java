package com.binaryBuddies.cinedore.ui.perfil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.binaryBuddies.cinedore.databinding.FragmentPerfilBinding;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private AuthApiService authApiService;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString("nombre", "Usuario"); // "Usuario" es el valor por defecto

        // Actualizar el TextView con el nombre del usuario
        binding.nombreUsuario.setText(nombreUsuario);

        // Inicializar Retrofit
        authApiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);

        // Configurar listeners para los botones
        binding.btnCerrarSesion.setOnClickListener(view -> logout());
        binding.continuaComoInvitado.setOnClickListener(view -> eliminarCuenta());

        return root;
    }

    private void logout() {
        authApiService.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("token");
                    editor.apply();

                    Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al cerrar sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eliminarCuenta() {
        authApiService.deleteAccount().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Cuenta eliminada", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                } else {
                    Toast.makeText(getContext(), "Error al eliminar cuenta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}