package Persistence;


import Logic.Afiliado;
import Logic.Evento;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class DAO_DisciplinaDeportiva {

    private Service service;
    MongoCollection<Document> collection;

    public DAO_DisciplinaDeportiva() {
        service = new Service();
        collection = service.getDataBase().getCollection("DisciplinasDeportivas");
    }

    public void Create(String id, String disciplina, List<ObjectId> miembros, List<ObjectId> eventos) {
        Document document= new Document();
        document.append("id",id);
        document.append("disciplina",disciplina);
        document.append("miembros",miembros);
        document.append("eventos",eventos);

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

    public void updateEventos(String id, ObjectId even) {
        Document document = collection.find(eq("id", id)).first();
        if (document != null) {

            List<ObjectId> listaObjectId = document.getList("eventos", ObjectId.class, new ArrayList<>());

            listaObjectId.add(even);

            Document updateDocument = new Document("eventos", listaObjectId);

            Bson updateOperation = Updates.set("eventos", listaObjectId);

            collection.updateOne(eq("id", id), updateOperation);
        }
    }


    public String findIdr (ObjectId id){
        Document document = collection.find(eq("_id",id)).first();
        if (document != null) {
            return (String) document.get("id");
        } else {
            return null;
        }
    }
    public ObjectId findObject (String id){
        Document document = collection.find(eq("id",id)).first();
        if (document != null) {
            return document.getObjectId("_id");
        } else {
            return null;
        }
    }
    public String returnObject(String id) {
        ObjectId objectId = new ObjectId(id);
        Document document = collection.find(eq("_id", objectId)).first();
        return document != null ? document.toJson() : "{}"; // Manejar el caso en que no se encuentre el documento
    }

    public void updateMiembros(String id, ObjectId objId) {

        Document document = collection.find(eq("id", id)).first();
        if (document != null) {
            List<ObjectId> listaObjectId = document.getList("miembros", ObjectId.class, new ArrayList<>());
            listaObjectId.add(objId);
            Document updateDocument = new Document("miembros", listaObjectId);
            Bson updateOperation = set("miembros", listaObjectId);
            collection.updateOne(eq("id", id), updateOperation);
        } else {
            System.out.println("No se encontró el documento con el ID: " + id);
        }
    }


    public void deleteMiembros(String id, ObjectId objId) {

        Document document = collection.find(eq("id", id)).first();
        if (document != null) {

            List<ObjectId> listaObjectId = document.getList("miembros", ObjectId.class, new ArrayList<>());

            listaObjectId.remove(objId);

            Document updateDocument = new Document("miembros", listaObjectId);

            Bson updateOperation = set("miembros", listaObjectId);

            collection.updateOne(eq("id", id), updateOperation);
        } else {

            System.out.println("No se encontró el documento con el ID: " + id);
        }
    }
    public void update(String id, String disciplina, List<ObjectId> miembros, List<Evento> eventos) {
        Document document1 = collection.find(eq("id", id)).first();

        Document document = new Document();
        document.append("disciplina",disciplina);
        document.append("miembros",miembros);
        document.append("eventos",eventos);

        Document update = new Document("$set", document);

        collection.updateOne(document1, update);
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

    public void delete(String id){
        Document document1 = collection.find(eq("id", id)).first();

        collection.deleteOne(document1);
    }
}
