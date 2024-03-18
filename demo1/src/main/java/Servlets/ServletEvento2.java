package Servlets;

import java.io.*;
import Persistence.DAO_Afiliado;
import Persistence.DAO_Eventos;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletEvento2", value = "/evento2-servlet")
public class ServletEvento2 extends HttpServlet {

    private DAO_Eventos eventos;

    @Override
    public void init() throws ServletException {
        super.init();
        eventos = new DAO_Eventos();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idEvento= request.getParameter("objectId");
        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(eventos.returnObject1(idEvento)));
    }
}