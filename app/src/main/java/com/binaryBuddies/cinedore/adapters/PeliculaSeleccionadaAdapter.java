package com.binaryBuddies.cinedore.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.binaryBuddies.cinedore.ui.peliculaSeleccionada.HorariosFragment;
import com.binaryBuddies.cinedore.ui.peliculaSeleccionada.SipnosisFragment;

public class PeliculaSeleccionadaAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public PeliculaSeleccionadaAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new HorariosFragment();
            case 1:
                return new SipnosisFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
