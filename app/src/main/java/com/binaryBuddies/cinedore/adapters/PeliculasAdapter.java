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

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PeliculaModel> peliculaModelList;

    public PeliculasAdapter(Context context, ArrayList<PeliculaModel> peliculaModelList) {
        this.context = context;
        this.peliculaModelList = (peliculaModelList != null) ? peliculaModelList : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pelicula, parent, false);
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

        // Manejar clics en la imagen
        holder.imagenPoster.setOnClickListener(v -> {
            Intent intent = new Intent(context, PeliculaSeleccionada.class);

            // Pasar los datos de la película seleccionada a la nueva actividad
            intent.putExtra("nombre", pelicula.getNombre());
            intent.putExtra("anio", pelicula.getAnio());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("imagenPoster", pelicula.getImagenPoster());
            intent.putExtra("categoria", pelicula.getCategoria());
            intent.putExtra("clasificacion", pelicula.getClasificacion());
            intent.putExtra("formato", pelicula.getFormato());
            intent.putExtra("lenguaje", pelicula.getLenguaje());

            // Iniciar la actividad
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return peliculaModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPoster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPoster = itemView.findViewById(R.id.imagenPoster);
        }
    }
}