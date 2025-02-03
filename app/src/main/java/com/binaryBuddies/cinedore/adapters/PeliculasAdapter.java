//package com.binaryBuddies.cinedore.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.tuapp.models.Pelicula;
//import com.tuapp.R;
//import java.util.List;
//
//public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.PeliculaViewHolder> {
//    private List<Pelicula> peliculas;
//    private Context context;
//
//    public PeliculasAdapter(List<Pelicula> peliculas, Context context) {
//        this.peliculas = peliculas;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
//        return new PeliculaViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
//        Pelicula pelicula = peliculas.get(position);
//        Glide.with(context).load(pelicula.getImagenUrl()).into(holder.poster);
//    }
//
//    @Override
//    public int getItemCount() {
//        return peliculas.size();
//    }
//
//    static class PeliculaViewHolder extends RecyclerView.ViewHolder {
//        ImageView poster;
//
//        public PeliculaViewHolder(@NonNull View itemView) {
//            super(itemView);
//            poster = itemView.findViewById(R.id.imagePelicula);
//        }
//    }
//}
//
