package com.binaryBuddies.cinedore.ui.peliculas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.binaryBuddies.cinedore.models.FormatoModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import com.binaryBuddies.cinedore.network.RetrofitClient;
import com.binaryBuddies.cinedore.services.PeliculasApiService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculasViewModel extends ViewModel {

    private final MutableLiveData<List<PeliculaModel>> peliculas;
    private final PeliculasApiService apiService;

    public PeliculasViewModel() {
        peliculas = new MutableLiveData<>();
        apiService = RetrofitClient.getRetrofitInstance().create(PeliculasApiService.class);
        cargarPeliculas();
    }

    public LiveData<List<PeliculaModel>> getPeliculas() {
        return peliculas;
    }

    private void cargarPeliculas() {
        apiService.getPeliculas().enqueue(new Callback<List<PeliculaModel>>() {
            @Override
            public void onResponse(Call<List<PeliculaModel>> call, Response<List<PeliculaModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    peliculas.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
