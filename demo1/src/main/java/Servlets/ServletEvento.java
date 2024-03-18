package Servlets;

import java.io.*;

import Persistence.DAO_Afiliado;
import Persistence.DAO_Eventos;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletEvento", value = "/evento-servlet")
public class ServletEvento extends HttpServlet {

    private DAO_Eventos eventos;

    @Override
    public void init() throws ServletException {
        super.init();
        eventos = new DAO_Eventos();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Gson gson = new Gson();

        if (eventos.Read() != null) {
            response.getWriter().write(gson.toJson(eventos.Read()));
        } else {
            response.getWriter().write(gson.toJson(null));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String puesto = request.getParameter("puesto");

        eventos.Create(eventos.generateId(), nombre,puesto);

    }
}