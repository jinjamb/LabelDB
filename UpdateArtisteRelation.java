import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;

public class UpdateArtisteRelation {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("labeldb");

        MongoCollection<Document> artistes = db.getCollection("artistes");
        MongoCollection<Document> groupes = db.getCollection("groupes");
        MongoCollection<Document> managers = db.getCollection("manager");

        List<Integer> groupeIds = groupes.find().map(doc -> doc.getInteger("id_groupe")).into(new ArrayList<>());
        List<Integer> managerIds = managers.find().map(doc -> doc.getInteger("id_manager")).into(new ArrayList<>());

        Random rand = new Random();

        for (Document artiste : artistes.find()) {
            int idArtiste = artiste.getInteger("id_artiste");
            Document fields = new Document();

            if (rand.nextDouble() < 0.6 && !groupeIds.isEmpty()) {
                int randomGroupe = groupeIds.get(rand.nextInt(groupeIds.size()));
                fields.append("groupe", randomGroupe);
            }
            if (!managerIds.isEmpty()) {
                int randomManager = managerIds.get(rand.nextInt(managerIds.size()));
                fields.append("manager_id", randomManager);
            }
            if (!fields.isEmpty()) {
                Document update = new Document("$set", fields);
                artistes.updateOne(new Document("id_artiste", idArtiste), update);
                System.out.println("Artiste " + idArtiste + " mis à jour : " + fields.toJson());
            }
        }

        mongoClient.close();
    }
}
