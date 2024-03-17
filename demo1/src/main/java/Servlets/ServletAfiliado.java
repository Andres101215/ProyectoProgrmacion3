package Servlets;

import java.io.*;
import Persistence.DAO_Afiliado;
import Persistence.DAO_DisciplinaDeportiva;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "ServletAfiliado", value = "/afiliado-servlet")
public class ServletAfiliado extends HttpServlet {

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

        Gson gson = new Gson();

        if( afiliado.Read() != null ) {
            response.getWriter().write(gson.toJson(afiliado.Read()));
        }else{
            response.getWriter().write(gson.toJson(null));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String id=request.getParameter("id");
         String nombre=request.getParameter("nombre");
         String apellido=request.getParameter("apellido");
         String documento=request.getParameter("documento");
         int edad=Integer.parseInt(request.getParameter("edad"));
         String genero=request.getParameter("genero");
         String direccion=request.getParameter("direccion");
         String telefono=request.getParameter("telefono");
         String correo=request.getParameter("correo");
         String idDisciplina= request.getParameter("idDisciplina");

         afiliado.Create(id,nombre,apellido,documento,edad,genero,direccion,telefono,correo,disciplina.find(idDisciplina));

        disciplina.updateMiembros(idDisciplina,afiliado.find(id));
    }
}