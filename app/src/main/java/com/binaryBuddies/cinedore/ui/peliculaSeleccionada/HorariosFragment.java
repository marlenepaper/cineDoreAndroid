package com.binaryBuddies.cinedore.ui.peliculaSeleccionada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.databinding.FragmentHorariosBinding;

import java.util.ArrayList;

public class HorariosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        ArrayList<String> funcionesFechas = getActivity().getIntent().getStringArrayListExtra("funciones_fechas");
        ArrayList<String> funcionesSalas = getActivity().getIntent().getStringArrayListExtra("funciones_salas");

        TextView tvFuncionesFechas = view.findViewById(R.id.funciones_fechas);
        TextView tvFuncionesSalas = view.findViewById(R.id.funciones_salas);

        if (funcionesFechas != null && !funcionesFechas.isEmpty() &&
                funcionesSalas != null && !funcionesSalas.isEmpty()) {

            tvFuncionesFechas.setText(String.join("\n", funcionesFechas));
            tvFuncionesSalas.setText(String.join("\n", funcionesSalas));
        } else {
            tvFuncionesFechas.setText("No disponible");
            tvFuncionesSalas.setText("No disponible");
        }

        return view;
    }
}
