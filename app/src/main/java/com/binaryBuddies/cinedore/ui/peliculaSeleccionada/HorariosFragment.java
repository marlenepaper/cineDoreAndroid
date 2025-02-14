package com.binaryBuddies.cinedore.ui.peliculaSeleccionada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.adapters.FuncionesAdapter;
import com.binaryBuddies.cinedore.databinding.FragmentHorariosBinding;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HorariosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        // Obtener la información de la película desde el Intent
        String nombre = getActivity().getIntent().getStringExtra("nombre");
        String anio = getActivity().getIntent().getStringExtra("anio");
        String duracion = getActivity().getIntent().getStringExtra("duracion");
        String sinopsis = getActivity().getIntent().getStringExtra("sinopsis");
        String imagenPoster = getActivity().getIntent().getStringExtra("imagenPoster");
        String categoria = getActivity().getIntent().getStringExtra("categoria");
        String clasificacion = getActivity().getIntent().getStringExtra("clasificacion");
        String lenguaje = getActivity().getIntent().getStringExtra("lenguaje");
        String color = getActivity().getIntent().getStringExtra("color");

        // Obtener funciones
        ArrayList<String> funcionesFechas = getActivity().getIntent().getStringArrayListExtra("funciones_fechas");
        ArrayList<String> funcionesSalas = getActivity().getIntent().getStringArrayListExtra("funciones_salas");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        List<FuncionModel> funciones = new ArrayList<>();
        for (int i = 0; i < funcionesFechas.size(); i++) {
            LocalDateTime fechaHora = LocalDateTime.parse(funcionesFechas.get(i), formatter);
            funciones.add(new FuncionModel(fechaHora, funcionesSalas.get(i)));
        }

        // Crear objeto de película
        PeliculaModel pelicula = new PeliculaModel(
                nombre, anio, duracion, sinopsis, imagenPoster,
                categoria, clasificacion, lenguaje, color, null, funciones
        );

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_funciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (!funciones.isEmpty()) {
            FuncionesAdapter adapter = new FuncionesAdapter(getContext(), pelicula, funciones);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}
