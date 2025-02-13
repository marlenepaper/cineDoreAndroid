package com.binaryBuddies.cinedore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binaryBuddies.cinedore.PeliculaSeleccionada;
import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.models.FormatoModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.MyViewHolder> {

    private final Context context;
    private final List<PeliculaModel> peliculaModelList;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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


            // Concatenar las funciones en un solo string y mostrarlas
//            List<FuncionModel> funciones = pelicula.getFunciones();
//            String funcionesTexto = (funciones != null) ? formatearFunciones(funciones) : "Sin funciones disponibles";

            // Manejar clic en la imagen para abrir la pantalla de detalles
            holder.imagenPoster.setOnClickListener(v -> {
                Intent intent = new Intent(context, PeliculaSeleccionada.class);
                intent.putExtra("nombre", pelicula.getNombre());
                intent.putExtra("anio", pelicula.getAnio());
                intent.putExtra("duracion", pelicula.getDuracion());
                intent.putExtra("sinopsis", pelicula.getSinopsis());
                intent.putExtra("imagenPoster", pelicula.getImagenPoster());
                intent.putExtra("categoria", pelicula.getCategoria());
                intent.putExtra("clasificacion", pelicula.getClasificacion());
                intent.putExtra("lenguaje", pelicula.getLenguaje());
                intent.putExtra("color", pelicula.getColor());



                intent.putStringArrayListExtra("formatos", obtenerFormatosComoString(pelicula.getFormato()));




//                intent.putExtra("funciones", funcionesTexto);
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




    private ArrayList<String> obtenerFormatosComoString(List<FormatoModel> formatos) {
        ArrayList<String> formatosString = new ArrayList<>();
        if (formatos != null) {
            for (FormatoModel formato : formatos) {
                String nombre = formato.getNombre();
                String detalle = formato.getDetalle();
                formatosString.add(nombre + " - " + detalle);
            }
        }
        return formatosString;
    }








    // Método para convertir la lista de funciones en un String
//    private String formatearFunciones(List<FuncionModel> funciones) {
//        if (funciones == null || funciones.isEmpty()) return "Sin funciones disponibles";
//        return funciones.stream()
//                .map(f -> f.getFecha() + " - " + f.getHora())
//                .collect(Collectors.joining("\n")); // Concatenar funciones separadas por saltos de línea
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPoster;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPoster = itemView.findViewById(R.id.imagenPoster);
        }
    }
}
