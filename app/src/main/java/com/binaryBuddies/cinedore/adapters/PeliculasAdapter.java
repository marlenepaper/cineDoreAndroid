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
import com.binaryBuddies.cinedore.models.CategoriaModel;
import com.binaryBuddies.cinedore.models.ClasificacionModel;
import com.binaryBuddies.cinedore.models.ColorModel;
import com.binaryBuddies.cinedore.models.FormatoModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.LenguajeModel;
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

            // Manejar clic en la imagen para abrir la pantalla de detalles
            holder.imagenPoster.setOnClickListener(v -> {
                Intent intent = new Intent(context, PeliculaSeleccionada.class);
                intent.putExtra("nombre", pelicula.getNombre());
                intent.putExtra("anio", pelicula.getAnio());
                intent.putExtra("duracion", pelicula.getDuracion());
                intent.putExtra("sinopsis", pelicula.getSinopsis());
                intent.putExtra("imagenPoster", pelicula.getImagenPoster());

                // Convertir listas de modelos a listas de Strings antes de pasarlas al Intent
                intent.putStringArrayListExtra("categoria", convertirCategoriasAString(pelicula.getCategoria()));
                intent.putStringArrayListExtra("clasificacion", convertirClasificacionesAString(pelicula.getClasificacion()));
                intent.putStringArrayListExtra("lenguaje", convertirLenguajesAString(pelicula.getLenguaje()));
                intent.putStringArrayListExtra("color", convertirColoresAString(pelicula.getColor()));

                intent.putStringArrayListExtra("formatos_nombres", obtenerFormatosNombres(pelicula.getFormato()));
                intent.putStringArrayListExtra("formatos_detalles", obtenerFormatosDetalles(pelicula.getFormato()));

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

    private ArrayList<String> obtenerFormatosNombres(List<FormatoModel> formatos) {
        ArrayList<String> nombres = new ArrayList<>();
        if (formatos != null) {
            for (FormatoModel formato : formatos) {
                nombres.add(formato.getNombre());
            }
        }
        return nombres;
    }

    private ArrayList<String> obtenerFormatosDetalles(List<FormatoModel> formatos) {
        ArrayList<String> detalles = new ArrayList<>();
        if (formatos != null) {
            for (FormatoModel formato : formatos) {
                detalles.add(formato.getDetalle());
            }
        }
        return detalles;
    }

    private ArrayList<String> obtenerFuncionesFechas(List<FuncionModel> funciones) {
        ArrayList<String> fechas = new ArrayList<>();
        if (funciones != null) {
            for (FuncionModel funcion : funciones) {
                fechas.add(funcion.getFechaHora().toString());
            }
        }
        return fechas;
    }

    private ArrayList<String> obtenerFuncionesSalas(List<FuncionModel> funciones) {
        ArrayList<String> salas = new ArrayList<>();
        if (funciones != null) {
            for (FuncionModel funcion : funciones) {
                if (funcion.getSala() != null) { // Solo verifica si sala no es null
                    salas.add(funcion.getSala().getNombre()); // Obtiene directamente el nombre
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
