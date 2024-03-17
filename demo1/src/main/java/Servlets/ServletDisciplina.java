package Servlets;


import java.io.*;
import java.util.ArrayList;

import Logic.Afiliado;
import Logic.Evento;
import Persistence.DAO_DisciplinaDeportiva;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletDisciplina", value = "/disciplina-servlet")
public class ServletDisciplina extends HttpServlet {

    private DAO_DisciplinaDeportiva disciplina;

    @Override
    public void init() throws ServletException {
        super.init();
        disciplina=new DAO_DisciplinaDeportiva();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Gson gson = new Gson();

        if (disciplina.Read() != null) {
            response.getWriter().write(gson.toJson(disciplina.Read()));
        } else {
            response.getWriter().write(gson.toJson(null));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String id=request.getParameter("idDisciplina1");
       String nombre=request.getParameter("disciplina");

       disciplina.Create(id,nombre,new ArrayList<Afiliado>(),new ArrayList<Evento>());
    }
}