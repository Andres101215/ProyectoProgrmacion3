package Persistence;

import Logic.DisciplinaDeportiva;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DAO_Afiliado {
    private Service service;
    MongoCollection<Document> collection;


    public DAO_Afiliado() {
        service = new Service();
        collection = service.getDataBase().getCollection("Afiliados");
    }

    public void Create(String id, String nombre, String apellido, String documento, int edad, String genero, String direccion,
                     String telefono, String correo, DisciplinaDeportiva disciplinaDeportiva) {
        Document document= new Document();
        document.append("id",id);
        document.append("nombre",nombre);
        document.append("apellido",apellido);
        document.append("documento",documento);
        document.append("edad",edad);
        document.append("genero",genero);
        document.append("direccion",direccion);
        document.append("telefono",telefono);
        document.append("correo",correo);
        document.append("disciplinaDeportiva",disciplinaDeportiva);
        collection.insertOne(document);
    }

    public Document Read(){
        FindIterable<Document> documentos = collection.find();

        return (Document) documentos;
    }
}
