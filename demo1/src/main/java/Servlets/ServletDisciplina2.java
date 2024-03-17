package Servlets;

import java.io.*;
import Persistence.DAO_Afiliado;
import Persistence.DAO_DisciplinaDeportiva;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletDisciplina2", value = "/disciplina2-servlet")
public class ServletDisciplina2 extends HttpServlet {

    private DAO_Afiliado afiliado;
    private DAO_DisciplinaDeportiva disciplina;

    @Override
    public void init() throws ServletException {
        super.init();
        afiliado= new DAO_Afiliado();
        disciplina= new DAO_DisciplinaDeportiva();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idDisciplina= request.getParameter("idDisciplina");

        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(disciplina.returnObject(idDisciplina)));
    }
}