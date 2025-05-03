import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerDAO {
    private final MongoCollection<Document> collection;

    public ManagerDAO(MongoDatabase db) {
        this.collection = db.getCollection("manager");
    }

    public void insertOne(Manager manager) {
        if (collection.find(new Document("id_manager", manager.getId_manager())).first() == null) {
            collection.insertOne(toDocument(manager));
            System.out.println("[CHECK] Manager inséré : " + manager.getNom());
        } else {
            System.out.println("[WARN] Manager avec id " + manager.getId_manager() + " existe déjà.");
        }
    }

    public void insertMany(List<Manager> managers) {
        List<Document> docs = managers.stream()
            .filter(m -> collection.find(new Document("id_manager", m.getId_manager())).first() == null)
            .map(this::toDocument)
            .collect(Collectors.toList());

        if (!docs.isEmpty()) {
            collection.insertMany(docs);
            System.out.println("[CHECK] Managers insérés : " + docs.size());
        } else {
            System.out.println("[WARN] Aucun manager inséré (doublons détectés)");
        }
    }

    public void updateOne(int id_manager, String nouvelEmail) {
        collection.updateOne(
            new Document("id_manager", id_manager),
            new Document("$set", new Document("email", nouvelEmail))
        );
    }

    public void updateMany(String nom, String nouvelEmail) {
        collection.updateMany(
            new Document("nom", nom),
            new Document("$set", new Document("email", nouvelEmail))
        );
    }

    public void deleteOne(int id_manager) {
        collection.deleteOne(new Document("id_manager", id_manager));
    }

    public void deleteMany(String nom) {
        collection.deleteMany(new Document("nom", nom));
    }

    private Document toDocument(Manager m) {
        Document doc = new Document("id_manager", m.getId_manager())
            .append("nom", m.getNom())
            .append("email", m.getEmail());

        if (m.getProducteurs() != null && !m.getProducteurs().isEmpty()) {
            doc.append("producteurs", m.getProducteurs());
        }

        return doc;
    }
}
