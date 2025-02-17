package com.binaryBuddies.cinedore.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.SeleccionBoletos;
import com.binaryBuddies.cinedore.models.CategoriaModel;
import com.binaryBuddies.cinedore.models.ClasificacionModel;
import com.binaryBuddies.cinedore.models.ColorModel;
import com.binaryBuddies.cinedore.models.LenguajeModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.binaryBuddies.cinedore.models.FuncionModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FuncionesAdapter extends RecyclerView.Adapter<FuncionesAdapter.FuncionViewHolder> {
    private final Context context;
    private final PeliculaModel pelicula;
    private final List<FuncionModel> funciones;

    public FuncionesAdapter(Context context, PeliculaModel pelicula, List<FuncionModel> funciones) {
        this.context = context;
        this.pelicula = pelicula;
        this.funciones = funciones;
    }

    @NonNull
    @Override
    public FuncionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horario, parent, false);
        return new FuncionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuncionViewHolder holder, int position) {
        FuncionModel funcion = funciones.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        holder.tvFuncionFecha.setText(capitalizeFirstLetter(funcion.getFechaHora().format(formatter)));
        holder.tvFuncionHora.setText(capitalizeFirstLetter(funcion.getFechaHora().format(formatterHora)));

        // Obtener el nombre de la primera sala sin mostrarla en el RecyclerView
        String nombreSala = "Sala Desconocida";
        if (funcion.getSala() != null) { // ✅ Solo verifica si no es null
            nombreSala = funcion.getSala().getNombre(); // ✅ Obtiene directamente el nombre
        }


        // Evento de clic para abrir la actividad con la información de la película y función
        String finalNombreSala = nombreSala;
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeleccionBoletos.class);

            // Pasar la información de la película
            intent.putExtra("nombre", pelicula.getNombre());
            intent.putExtra("anio", pelicula.getAnio());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("imagenPoster", pelicula.getImagenPoster());

            // Pasar la información de la función seleccionada
            intent.putExtra("fecha_funcion", funcion.getFechaHora().toString());
            intent.putExtra("sala_funcion", finalNombreSala); // ✅ La sala se envía pero no se muestra

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return funciones.size();
    }

    public static class FuncionViewHolder extends RecyclerView.ViewHolder {
        TextView tvFuncionFecha;
        TextView tvFuncionHora;


        public FuncionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFuncionFecha = itemView.findViewById(R.id.funcion_fecha);
            tvFuncionHora = itemView.findViewById(R.id.funcion_hora);

        }
    }

    // Método para capitalizar la primera letra de un texto
    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    // Métodos para convertir listas de modelos a listas de Strings

    private ArrayList<String> convertirCategoriasAString(List<CategoriaModel> categorias) {
        ArrayList<String> lista = new ArrayList<>();
        if (categorias != null) {
            for (CategoriaModel categoria : categorias) {
                lista.add(categoria.getCategoria());
            }
        }
        return lista;
    }

    private ArrayList<String> convertirClasificacionesAString(List<ClasificacionModel> clasificaciones) {
        ArrayList<String> lista = new ArrayList<>();
        if (clasificaciones != null) {
            for (ClasificacionModel clasificacion : clasificaciones) {
                lista.add(clasificacion.getNombre());
            }
        }
        return lista;
    }

    private ArrayList<String> convertirLenguajesAString(List<LenguajeModel> lenguajes) {
        ArrayList<String> lista = new ArrayList<>();
        if (lenguajes != null) {
            for (LenguajeModel lenguaje : lenguajes) {
                lista.add(lenguaje.getNombre());
            }
        }
        return lista;
    }

    private ArrayList<String> convertirColoresAString(List<ColorModel> colores) {
        ArrayList<String> lista = new ArrayList<>();
        if (colores != null) {
            for (ColorModel color : colores) {
                lista.add(color.getColor());
            }
        }
        return lista;
    }
}
