package com.binaryBuddies.cinedore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.binaryBuddies.cinedore.PeliculaSeleccionada;
import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;


public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.MyViewHolder> {
    private final Context context;
    private final List<PeliculaModel> peliculaModelList;

    public PeliculasAdapter(Context context, List<PeliculaModel> peliculaModelList) {
        this.context = context;
        this.peliculaModelList = (peliculaModelList != null) ? new ArrayList<>(peliculaModelList) : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PeliculaModel pelicula = peliculaModelList.get(position);

        if (pelicula != null) {
            GlideUrl glideUrl = new GlideUrl(pelicula.getImagenPoster(),
                    new LazyHeaders.Builder()
                            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .build());

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image);

            Glide.with(context)
                    .load(glideUrl)
                    .apply(requestOptions)
                    .into(holder.imagenPoster);

            holder.imagenPoster.setOnClickListener(v -> {
                Intent intent = new Intent(context, PeliculaSeleccionada.class);
                intent.putExtra("nombre", pelicula.getNombre());
                intent.putExtra("anio", pelicula.getAnio());
                intent.putExtra("duracion", pelicula.getDuracion());
                intent.putExtra("sinopsis", pelicula.getSinopsis());
                intent.putExtra("imagenPoster", pelicula.getImagenPoster());
                intent.putExtra("categoria", pelicula.getCategoria()); // Ahora es String
                intent.putExtra("clasificacion", pelicula.getClasificacion()); // Ahora es String
                intent.putExtra("lenguaje", pelicula.getLenguaje()); // Ahora es String
                intent.putExtra("color", pelicula.getColor()); // Ahora es String
                intent.putExtra("formato", pelicula.getFormato()); // Ahora es String

                intent.putStringArrayListExtra("funciones_fechas", obtenerFuncionesFechas(pelicula.getFunciones()));
                intent.putStringArrayListExtra("funciones_salas", obtenerFuncionesSalas(pelicula.getFunciones()));

                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return peliculaModelList.size();
    }

    // Método para actualizar la lista sin recrear el Adapter
    public void setPeliculas(List<PeliculaModel> nuevasPeliculas) {
        peliculaModelList.clear();
        peliculaModelList.addAll(nuevasPeliculas);
        notifyDataSetChanged();
    }

    private ArrayList<String> obtenerFuncionesFechas(List<FuncionModel> funciones) {
        ArrayList<String> fechas = new ArrayList<>();
        if (funciones != null) {
            for (FuncionModel funcion : funciones) {
                fechas.add(funcion.getFechaHora()); // Ya es String, no hace falta toString()
            }
        }
        return fechas;
    }

    private ArrayList<String> obtenerFuncionesSalas(List<FuncionModel> funciones) {
        ArrayList<String> salas = new ArrayList<>();
        if (funciones != null) {
            for (FuncionModel funcion : funciones) {
                if (funcion.getSala() != null) { // Solo verifica si sala no es null
                    salas.add(funcion.getSala()); // Ahora es String
                } else {
                    salas.add("Sala Desconocida");
                }
            }
        }
        return salas;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPoster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPoster = itemView.findViewById(R.id.imagenPoster);
        }
    }
}
