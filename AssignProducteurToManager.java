import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;


public class AssignProducteurToManager {
    
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("labeldb");

        MongoCollection<Document> manager = db.getCollection("manager");
        MongoCollection<Document> producteur = db.getCollection("producteur");
        List<Integer> producteurIds = producteur.find()
                .map(doc -> doc.getInteger("id_producteur"))
                .into(new ArrayList<>());
        if (producteurIds.isEmpty()) {
            System.out.println("Aucun producteur trouvé.");
            return;
        }
        Random rand = new Random();
        for (Document m : manager.find()) {
            int idManager = m.getInteger("id_manager");
            int idProducteur = producteurIds.get(rand.nextInt(producteurIds.size()));
            Document update = new Document("$set", new Document("producteurs", Collections.singletonList(idProducteur)));
            manager.updateOne(new Document("id_manager", idManager), update);
            System.out.println("Manager " + idManager + " reçoit producteur : " + idProducteur);
        }
        mongoClient.close();
    }
}
