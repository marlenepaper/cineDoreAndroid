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
import com.binaryBuddies.cinedore.models.CategoriaModel;
import com.binaryBuddies.cinedore.models.ClasificacionModel;
import com.binaryBuddies.cinedore.models.ColorModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.LenguajeModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.binaryBuddies.cinedore.models.SalaModel;

import java.time.LocalDateTime;
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

        ArrayList<String> categorias = getActivity().getIntent().getStringArrayListExtra("categoria");
        ArrayList<String> clasificaciones = getActivity().getIntent().getStringArrayListExtra("clasificacion");
        ArrayList<String> lenguajes = getActivity().getIntent().getStringArrayListExtra("lenguaje");
        ArrayList<String> colores = getActivity().getIntent().getStringArrayListExtra("color");

        // Obtener funciones
        ArrayList<String> funcionesFechas = getActivity().getIntent().getStringArrayListExtra("funciones_fechas");
        ArrayList<String> funcionesSalas = getActivity().getIntent().getStringArrayListExtra("funciones_salas");

        List<FuncionModel> funciones = new ArrayList<>();
        if (funcionesFechas != null && funcionesSalas != null) {
            for (int i = 0; i < funcionesFechas.size(); i++) {
                LocalDateTime fechaHora = LocalDateTime.parse(funcionesFechas.get(i));

                // Crear el objeto SalaModel (ya no es una lista)
                SalaModel sala = new SalaModel(funcionesSalas.get(i));

                // Crear el objeto FuncionModel con un solo SalaModel
                funciones.add(new FuncionModel(fechaHora, sala));
            }
        }

        // Convertir listas de String a listas de modelos adecuados
        List<CategoriaModel> categoriasModel = convertirCategorias(categorias);
        List<ClasificacionModel> clasificacionesModel = convertirClasificaciones(clasificaciones);
        List<LenguajeModel> lenguajesModel = convertirLenguajes(lenguajes);
        List<ColorModel> coloresModel = convertirColores(colores);

        // Crear objeto de película
        PeliculaModel pelicula = new PeliculaModel(
                nombre,
                anio,
                duracion,
                sinopsis,
                imagenPoster,
                categoriasModel,
                clasificacionesModel,
                lenguajesModel,
                coloresModel,
                null, // Formatos
                funciones
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

    private List<CategoriaModel> convertirCategorias(List<String> categorias) {
        List<CategoriaModel> lista = new ArrayList<>();
        if (categorias != null) {
            for (String categoria : categorias) {
                lista.add(new CategoriaModel(categoria));
            }
        }
        return lista;
    }

    private List<ClasificacionModel> convertirClasificaciones(List<String> clasificaciones) {
        List<ClasificacionModel> lista = new ArrayList<>();
        if (clasificaciones != null) {
            for (String clasificacion : clasificaciones) {
                lista.add(new ClasificacionModel(clasificacion, "Sin descripción"));
            }
        }
        return lista;
    }

    private List<LenguajeModel> convertirLenguajes(List<String> lenguajes) {
        List<LenguajeModel> lista = new ArrayList<>();
        if (lenguajes != null) {
            for (String lenguaje : lenguajes) {
                lista.add(new LenguajeModel(lenguaje, "Sin descripción"));
            }
        }
        return lista;
    }

    private List<ColorModel> convertirColores(List<String> colores) {
        List<ColorModel> lista = new ArrayList<>();
        if (colores != null) {
            for (String color : colores) {
                lista.add(new ColorModel(color));
            }
        }
        return lista;
    }
}
