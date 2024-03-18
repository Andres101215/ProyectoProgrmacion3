package Persistence;

import Logic.Evento;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Service {

    private String uri;
    private MongoClient mongoClient;

    private MongoDatabase dataBase;

    public Service() {
        uri="mongodb://localhost:27017";
        mongoClient = MongoClients.create(uri);
        dataBase = mongoClient.getDatabase("ClubDeportivo");
    }

    public MongoDatabase getDataBase() {
        return dataBase;
    }
}
