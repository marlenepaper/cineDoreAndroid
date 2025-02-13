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

public class SinopsisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sipnosis, container, false);
        String sinopsis = getActivity().getIntent().getStringExtra("sinopsis");
        TextView tvSinopsisDesarrollo = view.findViewById(R.id.sinopsis_desarrollo);
        tvSinopsisDesarrollo.setText(sinopsis);
        return view;
    }
}
