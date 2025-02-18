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
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import java.time.format.DateTimeFormatter;
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

        // Como `fechaHora` es un String, lo mostramos directamente sin formatear
        holder.tvFuncionFecha.setText(capitalizeFirstLetter(funcion.getFechaHora()));

        // Sala ahora es un String, por lo que ya no se accede a `getNombre()`
        String nombreSala = (funcion.getSala() != null) ? funcion.getSala() : "Sala Desconocida";

        // Evento de clic para abrir la actividad con la información de la película y función
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeleccionBoletos.class);

            // Pasar la información de la película
            intent.putExtra("nombre", pelicula.getNombre());
            intent.putExtra("anio", pelicula.getAnio());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("imagenPoster", pelicula.getImagenPoster());
            intent.putExtra("fecha_funcion", funcion.getFechaHora());
            intent.putExtra("sala_funcion", nombreSala);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return funciones.size();
    }

    public static class FuncionViewHolder extends RecyclerView.ViewHolder {
        TextView tvFuncionFecha;

        public FuncionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFuncionFecha = itemView.findViewById(R.id.funcion_fecha);
        }
    }

    // Método para capitalizar la primera letra de un texto
    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}

