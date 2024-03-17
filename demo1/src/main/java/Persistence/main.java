package Persistence;

import Logic.Afiliado;
import Logic.DisciplinaDeportiva;
import Logic.Evento;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        DAO_Afiliado dao = new DAO_Afiliado();
        DAO_DisciplinaDeportiva dao1 = new DAO_DisciplinaDeportiva();
           // System.out.println(dao1.Read());

       //dao1.Create("54421","Ajedrez",new ArrayList<ObjectId>(),new ArrayList<Evento>());
        dao.Create("12345","Andres Felipe","Puentes Montaña","1002740222",20,"Maculino","Cra 18#2-87","3154350306","puentesmontanaandresfelipe@gmail.com",dao1.find("54421"));

        //dao1.delete("54321");
        //dao.update("12345", "Andres Felipe ", "Puentes Montaña", "1002740222", 20, "Maculino", "Cra 18#2-87", "3154350306", "puentesmontanaandresfelipe@gmail.com", dao1.find("54321"));
    }
}