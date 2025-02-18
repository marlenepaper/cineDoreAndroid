package com.binaryBuddies.cinedore.adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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
import java.util.Locale;

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
        String fechaHoraStr = funcion.getFechaHora().toString(); // Verificar que es un String válido

        String fecha = fechaHoraStr;
        String hora = "";

        if (fechaHoraStr.contains("T")) { // Si es formato ISO-8601
            String[] fechaHora = fechaHoraStr.split("T");
            fecha = formatearFecha(fechaHora[0]); // yyyy-MM-dd → EEEE dd 'de' MMMM
            hora = fechaHora[1].substring(0, 5); // HH:mm:ss → HH:mm
        }

        holder.tvFuncionFecha.setText(capitalizeFirstLetter(fecha));
        holder.tvFuncionHora.setText(hora);


        String nombreSala = (funcion.getSala() != null) ? funcion.getSala() : "Sala Desconocida";

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeleccionBoletos.class);
            intent.putExtra("nombre", pelicula.getNombre());
            intent.putExtra("anio", pelicula.getAnio());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("imagenPoster", pelicula.getImagenPoster());
            intent.putExtra("fecha_funcion", fechaHoraStr);
            intent.putExtra("sala_funcion", nombreSala);
            intent.putExtra("lenguaje", pelicula.getLenguaje());
            intent.putExtra("clasificacion", pelicula.getClasificacion());

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

    /**
     * Convierte una fecha en formato yyyy-MM-dd a EEEE dd 'de' MMMM (ejemplo: "lunes 12 de febrero")
     */
    private String formatearFecha(String fechaISO) {
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat formatoSalida = new SimpleDateFormat("EEEE dd 'de' MMMM", new Locale("es", "ES"));

            return capitalizeFirstLetter(formatoSalida.format(formatoEntrada.parse(fechaISO)));
        } catch (Exception e) {
            return fechaISO;
        }
    }


    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}


