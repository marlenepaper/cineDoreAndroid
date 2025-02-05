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
            // Configurar Glide con GlideUrl para evitar bloqueos
            GlideUrl glideUrl = new GlideUrl(pelicula.getImagenPoster(),
                    new LazyHeaders.Builder()
                            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .build());

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.placeholder_image) // Imagen de carga
                    .error(R.drawable.error_image); // Imagen si falla la carga

            Glide.with(context)
                    .load(glideUrl)
                    .apply(requestOptions)
                    .into(holder.imagenPoster);
        }

        // Manejar clics en la imagen para abrir la pantalla de detalles
        holder.imagenPoster.setOnClickListener(v -> {
            Intent intent = new Intent(context, PeliculaSeleccionada.class);
            intent.putExtra("nombre", pelicula.getNombre());
            intent.putExtra("anio", pelicula.getAnio());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("imagenPoster", pelicula.getImagenPoster());
            intent.putExtra("categoria", pelicula.getCategoria());
            intent.putExtra("clasificacion", pelicula.getClasificacion());
            intent.putExtra("formato", pelicula.getFormato());
            intent.putExtra("lenguaje", pelicula.getLenguaje());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return peliculaModelList.size();
    }

    // Método para actualizar los datos sin recrear el adapter
    public void setPeliculas(List<PeliculaModel> nuevasPeliculas) {
        peliculaModelList.clear();
        peliculaModelList.addAll(nuevasPeliculas);
        notifyDataSetChanged(); // Notifica al RecyclerView que los datos han cambiado
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPoster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPoster = itemView.findViewById(R.id.imagenPoster);
        }
    }
}


