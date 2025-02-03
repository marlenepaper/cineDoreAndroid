package com.binaryBuddies.cinedore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PeliculaModel> peliculaModel;

    public PeliculasAdapter(Context context, ArrayList<PeliculaModel> peliculaModel) {
        this.context = context;
        this.peliculaModel = peliculaModel;
    }

    @NonNull
    @Override
    public PeliculasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pelicula, parent, false);
        return new PeliculasAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculasAdapter.MyViewHolder holder, int position) {
        // Usando Glide para cargar la imagen
        Glide.with(context)
                .load(peliculaModel.get(position).getImagenPoster()) // URL o recurso de imagen
                .into(holder.imagenPoster);
    }

    @Override
    public int getItemCount() {
        return (peliculaModel != null) ? peliculaModel.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPoster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPoster = itemView.findViewById(R.id.imagenPoster);
        }
    }
}
