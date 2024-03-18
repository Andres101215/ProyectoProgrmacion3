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
        System.out.println(dao1.Read());

        ArrayList<ObjectId> obj=new ArrayList<ObjectId>();
        ArrayList<Evento> even=new ArrayList<Evento>();
        obj.add( new ObjectId("65f77ea36bcc6719607dd4c6"));
        even.add(new Evento("123","Campeonato Nacional","1"));
      dao1.Create("54421","Futbol",obj,even);
        //dao.Create("12345","Andres Felipe","Puentes Montaña","1002740222",20,"Maculino","Cra 18#2-87","3154350306","puentesmontanaandresfelipe@gmail.com",dao1.find("54421"));


        //dao.delete("12345");
        //dao.update("12345", "Andres Felipe ", "Puentes Montaña", "1002740222", 20, "Maculino", "Cra 18#2-87", "3154350306", "puentesmontanaandresfelipe@gmail.com", dao1.find("54321"));
    }
}