package com.binaryBuddies.cinedore.ui.peliculaSeleccionada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.binaryBuddies.cinedore.R;

public class SinopsisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sinopsis, container, false);

        if (getActivity() == null || getActivity().getIntent() == null) {
            return view; // Previene NullPointerException
        }

        // Obtener sinopsis
        String sinopsis = getActivity().getIntent().getStringExtra("sinopsis");
        if (sinopsis == null || sinopsis.trim().isEmpty()) {
            sinopsis = "Sinopsis no disponible.";
        }

        TextView tvSinopsisDesarrollo = view.findViewById(R.id.sinopsis_desarrollo);
        tvSinopsisDesarrollo.setText(sinopsis);

        // Obtener formato
        String formato = getActivity().getIntent().getStringExtra("formato");
        if (formato == null || formato.trim().isEmpty()) {
            formato = "No disponible";
        }

        TextView tvFormato = view.findViewById(R.id.formato);
        tvFormato.setText(formato);

        // Obtener color
        String color = getActivity().getIntent().getStringExtra("color");
        if (color == null || color.trim().isEmpty()) {
            color = "No disponible";
        }

        TextView tvColor = view.findViewById(R.id.formato_color);
        tvColor.setText(color);

        return view;
    }
}
