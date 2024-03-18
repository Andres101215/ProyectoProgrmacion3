package Servlets;

import java.io.*;
import Persistence.DAO_Afiliado;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletAfiliado2", value = "/afiliado2-servlet")
public class ServletAfiliado2 extends HttpServlet {

    private DAO_Afiliado afiliado;

    @Override
    public void init() throws ServletException {
        super.init();
        afiliado= new DAO_Afiliado();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAfiliado= request.getParameter("objectId");
        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(afiliado.returnObject(idAfiliado)));
    }
}