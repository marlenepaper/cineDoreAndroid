package com.binaryBuddies.cinedore.ui.cine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.binaryBuddies.cinedore.databinding.FragmentCineBinding;


public class CineFragment extends Fragment {

    private FragmentCineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CineViewModel homeViewModel =
                new ViewModelProvider(this).get(CineViewModel.class);

        binding = FragmentCineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}