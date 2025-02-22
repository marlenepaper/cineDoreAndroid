package com.binaryBuddies.cinedore.ui.peliculaSeleccionada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.adapters.FuncionesAdapter;
import com.binaryBuddies.cinedore.models.*;


import java.util.ArrayList;
import java.util.List;

public class HorariosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) return view;

        int id = extras.getInt("id", 0);
        String nombre = extras.getString("nombre", "");
        int anio = extras.getInt("anio", 0);
        int duracion = extras.getInt("duracion", 0);
        String sinopsis = extras.getString("sinopsis", "");
        String imagenPoster = extras.getString("imagenPoster", "");

        // Obtener datos como String en lugar de objetos
        String categoria = extras.getString("categoria", "No disponible");
        String clasificacion = extras.getString("clasificacion", "No disponible");
        String formato = extras.getString("formato", "No disponible");
        String lenguaje = extras.getString("lenguaje", "No disponible");
        String color = extras.getString("color", "No disponible");

        // Obtener funciones
        ArrayList<String> funcionesFechas = extras.getStringArrayList("funciones_fechas");
        ArrayList<String> funcionesSalas = extras.getStringArrayList("funciones_salas");

        List<FuncionModel> funciones = new ArrayList<>();
        if (funcionesFechas != null && funcionesSalas != null) {
            int size = Math.min(funcionesFechas.size(), funcionesSalas.size());
            for (int i = 0; i < size; i++) {
                String fechaHora = funcionesFechas.get(i);
                String sala = funcionesSalas.get(i);
                funciones.add(new FuncionModel(0L, fechaHora, sala));
            }
        }

        // Crear objeto de película
        PeliculaModel pelicula = new PeliculaModel(id, nombre, anio, duracion, sinopsis, imagenPoster,
                categoria, clasificacion, lenguaje, color, formato, funciones);

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

