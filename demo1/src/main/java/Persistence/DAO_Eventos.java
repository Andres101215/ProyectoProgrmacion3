package Persistence;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class DAO_Eventos {
    private Service service;
    MongoCollection<Document> collection;


    public DAO_Eventos() {
        service = new Service();
        collection = service.getDataBase().getCollection("Eventos");
    }

    public ObjectId returnObject(String evento) {
        Document document = collection.find(eq("nombre", evento)).first();
        return document.getObjectId("_id");
    }

    public void Create(String id, String nombre, String puesto) {
        Document document = new Document();
        document.append("id", id);
        document.append("nombre", nombre);
        document.append("puesto", puesto);

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
