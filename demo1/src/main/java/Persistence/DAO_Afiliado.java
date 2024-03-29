package Persistence;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class DAO_Afiliado {
    private Service service;
    MongoCollection<Document> collection;


    public DAO_Afiliado() {
        service = new Service();
        collection = service.getDataBase().getCollection("Afiliados");
    }

    public String returnObject(String id) {
        ObjectId objectId = new ObjectId(id);
        Document document = collection.find(eq("_id", objectId)).first();
        return document != null ? document.toJson() : "{}";
    }

    public void Create(String id, String nombre, String apellido, String documento, int edad, String genero, String direccion,
                       String telefono, String correo, ObjectId idDisciplina) {
        Document document = new Document();
        document.append("id", id);
        document.append("nombre", nombre);
        document.append("apellido", apellido);
        document.append("documento", documento);
        document.append("edad", edad);
        document.append("genero", genero);
        document.append("direccion", direccion);
        document.append("telefono", telefono);
        document.append("correo", correo);
        document.append("Disciplina", idDisciplina);
        collection.insertOne(document);
    }

    public List<String> Read() {
        FindIterable<Document> documentos = collection.find();
        List<String> jsonDocuments = new ArrayList<>();
        for (Document documento : documentos) {
            jsonDocuments.add(documento.toJson());
        }
        return jsonDocuments;
    }

    public ObjectId findDisciplina(String id) {
        Document document = collection.find(eq("id", id)).first();
        if (document != null) {
            return document.getObjectId("Disciplina");
        } else {
            return null;
        }
    }
    public ObjectId find(String id) {
        Document document = collection.find(eq("id", id)).first();
        if (document != null) {
            return document.getObjectId("_id");
        } else {
            return null;
        }
    }

    public void update(String id, String nombre, String apellido, String documento, int edad, String genero, String direccion,
                       String telefono, String correo, ObjectId idDisciplina) {
        Document document1 = collection.find(eq("id", id)).first();

        Document document = new Document();
        document.append("nombre", nombre);
        document.append("apellido", apellido);
        document.append("documento", documento);
        document.append("edad", edad);
        document.append("genero", genero);
        document.append("direccion", direccion);
        document.append("telefono", telefono);
        document.append("correo", correo);
        document.append("Disciplina", idDisciplina);

        Document update = new Document("$set", document);

        collection.updateOne(document1, update);
    }

    public void delete(String id) {
        Document document1 = collection.find(eq("id", id)).first();
        System.out.println("entra al metodo");

        collection.deleteOne(document1);
    }

    public String generateId() {
        String id;
        Random random = new Random();
        do {
            int numeroAleatorio = random.nextInt(90000) + 10000;
            id = String.valueOf(numeroAleatorio);
        } while (collection.find(eq("id", id)).first() != null);
        return id;
    }

}
