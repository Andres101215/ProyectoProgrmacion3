package Logic;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDeportiva {

    private String disciplina;

    private List<Afiliado> miembros;

    private List<Evento> eventos;


    public DisciplinaDeportiva() {
    }

    public DisciplinaDeportiva(String disciplina, List<Afiliado> miembros, List<Evento> eventos) {
        this.disciplina = disciplina;
        this.miembros = miembros;
        this.eventos = eventos;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Afiliado> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Afiliado> miembros) {
        this.miembros = miembros;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
