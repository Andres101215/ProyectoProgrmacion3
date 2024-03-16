package Logic;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDeportiva {

    private String disciplina;

    private List<Afiliado> miembros;

    private List<Evento> eventos;


    public DisciplinaDeportiva() {
        miembros=new ArrayList<>();
        eventos= new ArrayList<>();
    }

    public DisciplinaDeportiva(String disciplina, List<Afiliado> miembros, List<Evento> eventos) {
        this.disciplina = disciplina;
        this.miembros = miembros;
        this.eventos = eventos;
    }
}
