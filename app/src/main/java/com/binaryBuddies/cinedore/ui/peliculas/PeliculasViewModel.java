package com.binaryBuddies.cinedore.ui.peliculas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.binaryBuddies.cinedore.models.FormatoModel;
import com.binaryBuddies.cinedore.models.FuncionModel;
import com.binaryBuddies.cinedore.models.PeliculaModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PeliculasViewModel extends ViewModel {

    private final MutableLiveData<List<PeliculaModel>> peliculas;

    public PeliculasViewModel() {
        peliculas = new MutableLiveData<>();
        cargarPeliculas();
    }

    public LiveData<List<PeliculaModel>> getPeliculas() {
        return peliculas;
    }

    private void cargarPeliculas() {

        List<PeliculaModel> lista = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy HH:mm");

        lista.add(new PeliculaModel(
                "Tierra y libertad",
                "1995",
                "109 min",
                "Un joven británico se une a las milicias del POUM durante la Guerra Civil Española, experimentando las complejidades y divisiones internas del conflicto.",
                "https://pics.filmaffinity.com/Tierra_y_libertad-753059412-large.jpg",
                "Drama",
                "+16",
                "VOSE",
                "Color",
                List.of(new FormatoModel("35 mm", "Proyección en formato 35 mm")),
                List.of(
                        new FuncionModel(LocalDateTime.parse("09 de febrero de 2025 17:30", formatter), "SALA 1"),
                        new FuncionModel(LocalDateTime.parse("20 de febrero de 2025 17:30", formatter), "SALA 1")
                )
        ));

        lista.add(new PeliculaModel(
                "Tata mía",
                "1986",
                "100 min",
                "Una mujer madura y su sobrina se ven envueltas en una serie de enredos y situaciones cómicas mientras intentan sobrevivir en la España de la posguerra.",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.filmaffinity.com%2Fes%2Ffilm293953.html",
                "Comedia",
                "TP",
                "VE",
                "Color",
                List.of(new FormatoModel("35 mm", "Proyección en formato 35 mm")),
                List.of(new FuncionModel(LocalDateTime.parse("09 de febrero de 2025 19:30", formatter), "SALA 2"))
        ));

        lista.add(new PeliculaModel(
                "Fueros humanos",
                "1935",
                "95 min",
                "Un hombre inocente es condenado por un crimen que no cometió y, tras escapar de prisión, busca al verdadero culpable mientras es perseguido por la ley.",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fcartelesmix.es%2Fcartelesdecine%2F%3Fp%3D8718",
                "Crimen",
                "+12",
                "VOSE",
                "B/N",
                List.of(new FormatoModel("35 mm", "Proyección en formato 35 mm")),
                List.of(new FuncionModel(LocalDateTime.parse("09 de febrero de 2025 21:00", formatter), "SALA 1"))
        ));

        lista.add(new PeliculaModel(
                "La balsa de piedra",
                "2002",
                "110 min",
                "Una grieta separa la península ibérica del resto de Europa, convirtiéndola en una isla a la deriva en el Atlántico, mientras un grupo de personajes busca entender este fenómeno.",
                "https://m.media-amazon.com/images/M/MV5BMTkyNzM3NTM3OF5BMl5BanBnXkFtZTcwODcxMjkyMQ@@._V1_.jpg",
                "Fantasía",
                "+12",
                "VE",
                "Color",
                List.of(new FormatoModel("35 mm", "Proyección en formato 35 mm")),
                List.of(new FuncionModel(LocalDateTime.parse("11 de febrero de 2025 17:30", formatter), "SALA 1"))
        ));

        lista.add(new PeliculaModel(
                "En un lugar solitario",
                "1950",
                "94 min",
                "Un guionista de Hollywood con un temperamento violento es sospechoso de asesinato, mientras inicia una relación con su vecina, quien comienza a dudar de su inocencia.",
                "https://pics.filmaffinity.com/En_un_lugar_solitario-805021886-large.jpg",
                "Misterio",
                "+16",
                "VOSE",
                "B/N",
                List.of(new FormatoModel("35 mm", "Proyección en formato 35 mm")),
                List.of(new FuncionModel(LocalDateTime.parse("11 de febrero de 2025 21:00", formatter), "SALA 1"))
        ));

        lista.add(new PeliculaModel(
                "Yuli",
                "2018",
                "111 min",
                "Biografía del bailarín cubano Carlos Acosta, que narra su ascenso desde las calles de La Habana hasta convertirse en una estrella del ballet internacional.",
                "https://m.media-amazon.com/images/M/MV5BNzNkN2JjNWYtYzg1OC00NTE0LWFiZWMtYTdiZjhlZDFiNmZlXkEyXkFqcGc@._V1_.jpg",
                "Biografía",
                "+7",
                "VOSE",
                "Color",
                List.of(new FormatoModel("Digital", "Proyección en formato digital")),
                List.of(new FuncionModel(LocalDateTime.parse("14 de febrero de 2025 19:30", formatter), "SALA 2"))
        ));

        peliculas.setValue(lista);
    }
}
