package Servlets;


import java.io.*;
import java.util.ArrayList;

import Logic.Afiliado;
import Logic.Evento;
import Persistence.DAO_DisciplinaDeportiva;
import Persistence.DAO_Eventos;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bson.types.ObjectId;

@WebServlet(name = "ServletDisciplina", value = "/disciplina-servlet")
public class ServletDisciplina extends HttpServlet {

    private DAO_DisciplinaDeportiva disciplina;

    private DAO_Eventos evento;

    @Override
    public void init() throws ServletException {
        super.init();
        disciplina=new DAO_DisciplinaDeportiva();
        evento= new DAO_Eventos();
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
       String id=request.getParameter("idDisciplina");
       String nombre=request.getParameter("disciplina");
        String nombreEvento=request.getParameter("evento");
        String stat =request.getParameter("stat");

        switch (stat){
            case "1":
                id=disciplina.generateId();
                disciplina.Create(id,nombre,new ArrayList<ObjectId>(),new ArrayList<ObjectId>());
                break;
            case "2":
                disciplina.updateEventos(id,evento.returnObject(nombreEvento));

                break;
            case "3":
                disciplina.delete(id);
                break;
        }


    }
}