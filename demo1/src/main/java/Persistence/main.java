package Persistence;

import Logic.Afiliado;
import Logic.DisciplinaDeportiva;
import Logic.Evento;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        DAO_Afiliado dao = new DAO_Afiliado();
        DAO_DisciplinaDeportiva dao1 = new DAO_DisciplinaDeportiva();
        //dao1.Create("54321","Ajedrez",new ArrayList<Afiliado>(),new ArrayList<Evento>());
        //dao.Create("12345","Andres Felipe","Puentes Montaña","1002740222",20,"Maculino","Cra 18#2-87","3154350306","puentesmontanaandresfelipe@gmail.com",dao1.find("54321"));

        //dao1.delete("54321");
        //dao.update("12345", "Andres Felipe ", "Puentes Montaña", "1002740222", 20, "Maculino", "Cra 18#2-87", "3154350306", "puentesmontanaandresfelipe@gmail.com", dao1.find("54321"));
    }
}