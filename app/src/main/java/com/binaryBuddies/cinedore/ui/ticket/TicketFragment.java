package com.binaryBuddies.cinedore.ui.ticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.binaryBuddies.cinedore.databinding.FragmentTicketQrBinding;

public class TicketFragment extends Fragment {

    private FragmentTicketQrBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TicketViewModel dashboardViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

        binding = FragmentTicketQrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        boolean hayCompras = verificarSiHayCompras();

        if (hayCompras) {
            binding.backgroundSinCompras.setVisibility(View.GONE);
            binding.imagenPelicula.setVisibility(View.VISIBLE);
            binding.ticketLayout.setVisibility(View.VISIBLE);
        } else {
            binding.backgroundSinCompras.setVisibility(View.VISIBLE);
            binding.imagenPelicula.setVisibility(View.GONE);
            binding.ticketLayout.setVisibility(View.GONE);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private boolean verificarSiHayCompras() {
        // Aquí deberías verificar los datos de tus tickets
        // Por ahora, retornamos false como ejemplo
        return false;
    }
}