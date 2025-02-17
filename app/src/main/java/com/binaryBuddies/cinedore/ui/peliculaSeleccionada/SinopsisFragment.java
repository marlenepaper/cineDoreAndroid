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

import java.util.ArrayList;

public class SinopsisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sipnosis, container, false);

        String sinopsis = getActivity().getIntent().getStringExtra("sinopsis");
        TextView tvSinopsisDesarrollo = view.findViewById(R.id.sinopsis_desarrollo);
        tvSinopsisDesarrollo.setText(sinopsis);

        ArrayList<String> formatosNombres = getActivity().getIntent().getStringArrayListExtra("formatos_nombres");
        ArrayList<String> formatosDetalles = getActivity().getIntent().getStringArrayListExtra("formatos_detalles");

        TextView tvFormato = view.findViewById(R.id.formato);
        TextView tvFormatoDescripcion = view.findViewById(R.id.formato_descipcion);

        if (formatosNombres != null && !formatosNombres.isEmpty() &&
                formatosDetalles != null && !formatosDetalles.isEmpty()) {
            tvFormato.setText(String.join("\n", formatosNombres));
            tvFormatoDescripcion.setText(String.join("\n", formatosDetalles));
        } else {
            tvFormato.setText("No disponible");
            tvFormatoDescripcion.setText("No disponible");
        }

        // 🔧 CORRECCIÓN: Obtener "color" como lista
        ArrayList<String> colores = getActivity().getIntent().getStringArrayListExtra("color");
        String coloresTexto = (colores != null && !colores.isEmpty()) ? String.join(", ", colores) : "No disponible";

        TextView tvColor = view.findViewById(R.id.formato_color);
        tvColor.setText(coloresTexto);

        return view;
    }
}