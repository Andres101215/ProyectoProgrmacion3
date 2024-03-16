package Persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


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
