package com.binaryBuddies.cinedore.ui.peliculas;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binaryBuddies.cinedore.R;
import com.binaryBuddies.cinedore.adapters.PeliculasAdapter;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import java.util.ArrayList;

public class PeliculasFragment extends Fragment {

    private RecyclerView recyclerView;
    private PeliculasAdapter peliculasAdapter;
    private ArrayList<PeliculaModel> listaPeliculas;

    public PeliculasFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);

        recyclerView = view.findViewById(R.id.recyclerPeliculas);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        cargarPeliculas();

        peliculasAdapter = new PeliculasAdapter(getContext(), listaPeliculas);
        recyclerView.setAdapter(peliculasAdapter);


        return view;
    }

    private void cargarPeliculas() {
        listaPeliculas = new ArrayList<>();

        listaPeliculas.add(new PeliculaModel(
                "Yuli",
                "2018",
                "115 min",
                "Biografía del bailarín cubano Carlos Acosta, que narra su infancia en La Habana y su ascenso en el mundo de la danza.",
                "https://image.tmdb.org/t/p/w500/9bqWVpLt3MxRyyHumd15yIG3uz3.jpg",
                "Drama",
                "NR",
                "Digital",
                "Español"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Los sobornados",
                "1953",
                "89 min",
                "Un sargento de policía lucha contra la corrupción dentro de su propio departamento mientras intenta desmantelar una red criminal.",
                "https://cineszocomajadahonda.org/wp-content/uploads/20207953.jpg",
                "Cine negro",
                "NR",
                "35 mm",
                "Inglés"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Otra vez juntos",
                "1940",
                "92 min",
                "Una pareja divorciada se reencuentra y comienza a reconsiderar su relación mientras trabajan juntos en una producción teatral.",
                "https://www.epdlp.com/fotos/moraweck1.jpg",
                "Comedia",
                "NR",
                "Digital",
                "Inglés"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Malaventura",
                "1988",
                "90 min",
                "Una serie de viñetas que retratan la vida nocturna y los personajes marginales de una ciudad española.",
                "https://pics.filmaffinity.com/Malaventura-789149013-large.jpg",
                "Drama",
                "NR",
                "Digital",
                "Español"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Always a Bridesmaid",
                "2019",
                "97 min",
                "Una mujer reflexiona sobre su vida amorosa y decide enfrentar sus miedos al compromiso después de ser dama de honor en múltiples bodas.",
                "https://m.media-amazon.com/images/M/MV5BMDIxYWE0ZmEtYjM2YS00NWVjLWI0YWYtMWIzMzA5NGY4ODY4XkEyXkFqcGc@._V1_.jpg",
                "Comedia",
                "NR",
                "Digital",
                "Inglés"
        ));

        listaPeliculas.add(new PeliculaModel(
                "La boda de Rosa",
                "2020",
                "97 min",
                "Rosa, a punto de cumplir 45 años, decide tomar las riendas de su vida y casarse consigo misma en una ceremonia simbólica.",
                "https://pics.filmaffinity.com/La_boda_de_Rosa-406236302-large.jpg",
                "Comedia",
                "NR",
                "Digital",
                "Español"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Diaries",
                "1980",
                "90 min",
                "Un documental que explora la vida cotidiana y las reflexiones personales del director a través de sus diarios filmados.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkFWjEyhIZtCAriZf6YA8Wq8YaBaPRSjehLw&s",
                "Documental",
                "NR",
                "16 mm",
                "Inglés"
        ));

        listaPeliculas.add(new PeliculaModel(
                "Camada negra",
                "1977",
                "100 min",
                "Un joven se ve envuelto en un grupo neonazi en la España de la Transición, explorando temas de violencia y fanatismo.",
                "https://m.media-amazon.com/images/M/MV5BNDBhMGIyYjMtYmJmOS00MzBmLWJhMmEtMDE2ZTMwOWUyMGU4XkEyXkFqcGc@._V1_.jpg",
                "Drama",
                "NR",
                "Digital",
                "Español"
        ));
    }
}
