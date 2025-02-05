package com.binaryBuddies.cinedore.ui.peliculas;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.adapters.PeliculasAdapter;
import com.binaryBuddies.cinedore.databinding.FragmentPeliculasBinding;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import java.util.ArrayList;
import java.util.List;

public class PeliculasFragment extends Fragment {

    private FragmentPeliculasBinding binding;
    private PeliculasViewModel peliculasViewModel;
    private PeliculasAdapter peliculasAdapter;

    public PeliculasFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Habilitar ViewBinding
        binding = FragmentPeliculasBinding.inflate(inflater, container, false);
        View root = binding.getRoot(); // Obtener la vista raíz

        // Configurar RecyclerView usando binding
        binding.recyclerPeliculas.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        peliculasAdapter = new PeliculasAdapter(requireContext(), new ArrayList<>());
        binding.recyclerPeliculas.setAdapter(peliculasAdapter);

        peliculasViewModel = new ViewModelProvider(this).get(PeliculasViewModel.class);

        // Observar cambios en la lista y actualizar el adapter
        peliculasViewModel.getPeliculas().observe(getViewLifecycleOwner(), lista -> {
            peliculasAdapter.setPeliculas(lista);  // Nuevo método para actualizar datos
            peliculasAdapter.notifyDataSetChanged();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitar fugas de memoria
    }
}




