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
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;
import java.util.ArrayList;
import java.util.List;

public class PeliculasFragment extends Fragment {


    private ArrayList<PeliculaModel> listaPeliculas=new ArrayList<>();

    public PeliculasFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerPeliculas);
        cargarPeliculas();

        PeliculasAdapter peliculasAdapter = new PeliculasAdapter(requireContext(), listaPeliculas);

        recyclerView.setAdapter(peliculasAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return view;
    }



    private void cargarPeliculas() {

        // Lista de funciones para cada película (todas en febrero)

        List<FuncionModel> funcionesYuli = new ArrayList<>();
        funcionesYuli.add(new FuncionModel("Lunes 5 de Febrero", "17:00"));
        funcionesYuli.add(new FuncionModel("Jueves 8 de Febrero", "18:30"));
        funcionesYuli.add(new FuncionModel("Sábado 10 de Febrero", "20:45"));

        List<FuncionModel> funcionesSobornados = new ArrayList<>();
        funcionesSobornados.add(new FuncionModel("Martes 6 de Febrero", "16:00"));
        funcionesSobornados.add(new FuncionModel("Viernes 9 de Febrero", "19:15"));

        List<FuncionModel> funcionesOtraVezJuntos = new ArrayList<>();
        funcionesOtraVezJuntos.add(new FuncionModel("Miércoles 7 de Febrero", "18:00"));
        funcionesOtraVezJuntos.add(new FuncionModel("Domingo 11 de Febrero", "21:00"));

        List<FuncionModel> funcionesMalaventura = new ArrayList<>();
        funcionesMalaventura.add(new FuncionModel("Jueves 15 de Febrero", "20:00"));
        funcionesMalaventura.add(new FuncionModel("Sábado 17 de Febrero", "22:30"));

        List<FuncionModel> funcionesBridesmaid = new ArrayList<>();
        funcionesBridesmaid.add(new FuncionModel("Viernes 16 de Febrero", "19:00"));
        funcionesBridesmaid.add(new FuncionModel("Lunes 19 de Febrero", "21:15"));

        List<FuncionModel> funcionesBodaRosa = new ArrayList<>();
        funcionesBodaRosa.add(new FuncionModel("Sábado 24 de Febrero", "16:30"));
        funcionesBodaRosa.add(new FuncionModel("Martes 27 de Febrero", "18:45"));

        List<FuncionModel> funcionesDiaries = new ArrayList<>();
        funcionesDiaries.add(new FuncionModel("Domingo 18 de Febrero", "17:00"));
        funcionesDiaries.add(new FuncionModel("Miércoles 28 de Febrero", "19:30"));

        List<FuncionModel> funcionesCamadaNegra = new ArrayList<>();
        funcionesCamadaNegra.add(new FuncionModel("Viernes 23 de Febrero", "20:00"));
        funcionesCamadaNegra.add(new FuncionModel("Lunes 26 de Febrero", "22:00"));

        // Agregar películas con sus funciones
        listaPeliculas.add(new PeliculaModel(
                "Yuli",
                "2018",
                "115 min",
                "Biografía del bailarín cubano Carlos Acosta, que narra su infancia en La Habana y su ascenso en el mundo de la danza.",
                "https://image.tmdb.org/t/p/w500/9bqWVpLt3MxRyyHumd15yIG3uz3.jpg",
                "Drama",
                "NR",
                "Digital",
                "Español",
                funcionesYuli
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
                "Inglés",
                funcionesSobornados
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
                "Inglés",
                funcionesOtraVezJuntos
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
                "Español",
                funcionesMalaventura
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
                "Inglés",
                funcionesBridesmaid
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
                "Español",
                funcionesBodaRosa
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
                "Inglés",
                funcionesDiaries
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
                "Español",
                funcionesCamadaNegra
        ));
    }
}
