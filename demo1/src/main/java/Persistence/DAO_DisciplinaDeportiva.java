package Persistence;


import Logic.Afiliado;
import Logic.Evento;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DAO_DisciplinaDeportiva {

    private Service service;
    MongoCollection<Document> collection;

    public DAO_DisciplinaDeportiva() {
        service = new Service();
        collection = service.getDataBase().getCollection("DisciplinasDeportivas");
    }

    public void Create(String id, String disciplina, List<Afiliado> miembros, List<Evento> eventos) {
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
    public ObjectId find (String id){
        Document document = collection.find(eq("id",id)).first();
        if (document != null) {
            return document.getObjectId("_id");
        } else {
            return null;
        }
    }
    public void update(String id, String disciplina, List<Afiliado> miembros, List<Evento> eventos) {
        Document document1 = collection.find(eq("id", id)).first();

        Document document = new Document();
        document.append("disciplina",disciplina);
        document.append("miembros",miembros);
        document.append("eventos",eventos);

        Document update = new Document("$set", document);

        collection.updateOne(document1, update);
    }
    public void delete(String id){
        Document document1 = collection.find(eq("id", id)).first();

        collection.deleteOne(document1);
    }
}
