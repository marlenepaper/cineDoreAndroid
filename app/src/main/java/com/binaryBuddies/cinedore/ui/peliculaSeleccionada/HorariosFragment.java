package com.binaryBuddies.cinedore.ui.peliculaSeleccionada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.databinding.FragmentHorariosBinding;

public class HorariosFragment extends Fragment {

    private FragmentHorariosBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHorariosBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); // Guarda la vista antes de retornarla

        // Obtener el argumento pasado desde PeliculaSeleccionada
        Bundle args = getArguments();
        if (args != null) {
            String funcionesTexto = args.getString("funciones", "Sin funciones disponibles");
            binding.funcionesPelicula.setText(funcionesTexto);
        }

        return view; // Retorna la vista después de modificarla

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evita fugas de memoria
    }
}
