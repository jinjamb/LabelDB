import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class GroupeDAO {
    private final MongoCollection<Document> collection;

    public GroupeDAO(MongoDatabase db) {
        this.collection = db.getCollection("groupes");
    }

    public void insertOne(Groupe groupe) {
        if (collection.find(new Document("id_groupe", groupe.getId_groupe())).first() == null) {
            collection.insertOne(toDocument(groupe));
            System.out.println("[CHECK] Groupe inséré : " + groupe.getNom());
        } else {
            System.out.println("[WARN] Groupe avec id " + groupe.getId_groupe() + " existe déjà.");
        }
    }

    public void insertMany(List<Groupe> groupes) {
        List<Document> docs = groupes.stream()
            .filter(g -> collection.find(new Document("id_groupe", g.getId_groupe())).first() == null)
            .map(this::toDocument)
            .collect(Collectors.toList());

        if (!docs.isEmpty()) {
            collection.insertMany(docs);
            System.out.println("[CHECK] Groupes insérés : " + docs.size());
        } else {
            System.out.println("[WARN] Aucun groupe inséré (doublons détectés)");
        }
    }

    public void updateOne(int id_groupe, String nouveauNom) {
        collection.updateOne(
            new Document("id_groupe", id_groupe),
            new Document("$set", new Document("nom", nouveauNom))
        );
    }

    public void updateMany(String genre, String nouveauNom) {
        collection.updateMany(
            new Document("genre_musical", genre),
            new Document("$set", new Document("nom", nouveauNom))
        );
    }

    public void deleteOne(int id_groupe) {
        collection.deleteOne(new Document("id_groupe", id_groupe));
    }

    public void deleteMany(String genre) {
        collection.deleteMany(new Document("genre_musical", genre));
    }

    private Document toDocument(Groupe g) {
        return new Document("id_groupe", g.getId_groupe())
            .append("nom", g.getNom())
            .append("genre_musical", g.getGenre_musical());
    }
}
