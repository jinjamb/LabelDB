import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class ProducteurDAO {
    private final MongoCollection<Document> collection;

    public ProducteurDAO(MongoDatabase db) {
        this.collection = db.getCollection("producteur");
    }

    public void insertOne(Producteur producteur) {
        if (collection.find(new Document("id_producteur", producteur.getId_producteur())).first() == null) {
            collection.insertOne(toDocument(producteur));
            System.out.println("[CHECK] Producteur inséré : " + producteur.getNom());
        } else {
            System.out.println("[WARN] Producteur avec id " + producteur.getId_producteur() + " existe déjà.");
        }
    }

    public void insertMany(List<Producteur> producteurs) {
        List<Document> docs = producteurs.stream()
            .filter(p -> collection.find(new Document("id_producteur", p.getId_producteur())).first() == null)
            .map(this::toDocument)
            .collect(Collectors.toList());

        if (!docs.isEmpty()) {
            collection.insertMany(docs);
            System.out.println("[CHECK] Producteurs insérés : " + docs.size());
        } else {
            System.out.println("[WARN] Aucun producteur inséré (doublons détectés)");
        }
    }

    public void updateOne(int id_producteur, String nouvelleSociete) {
        collection.updateOne(
            new Document("id_producteur", id_producteur),
            new Document("$set", new Document("societe", nouvelleSociete))
        );
    }

    public void updateMany(String nom, String nouvelleSociete) {
        collection.updateMany(
            new Document("nom", nom),
            new Document("$set", new Document("societe", nouvelleSociete))
        );
    }

    public void deleteOne(int id_producteur) {
        collection.deleteOne(new Document("id_producteur", id_producteur));
    }

    public void deleteMany(String nom) {
        collection.deleteMany(new Document("nom", nom));
    }

    private Document toDocument(Producteur p) {
        return new Document("id_producteur", p.getId_producteur())
            .append("nom", p.getNom())
            .append("societe", p.getSociete());
    }
}

