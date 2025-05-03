import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MusiqueDAO {
    private final MongoCollection<Document> collection;

    public MusiqueDAO(MongoDatabase db) {
        this.collection = db.getCollection("musique");
    }


    public void insertOne(Musique musique) {
        if (collection.find(new Document("id_musique", musique.getId_musique())).first() == null) {
            collection.insertOne(toDocument(musique));
            System.out.println("[VALIDATE] Musique insérée : " + musique.getTitre());
        } else {
            System.out.println("[WARNING] La musique avec id " + musique.getId_musique() + " existe déjà.");
        }
    }


    public void insertMany(List<Musique> musiques) {
        List<Document> docs = musiques.stream()
            .filter(m -> collection.find(new Document("id_musique", m.getId_musique())).first() == null)
            .map(this::toDocument)
            .collect(Collectors.toList());

        if (!docs.isEmpty()) {
            collection.insertMany(docs);
            System.out.println("[VALIDATE] Musiques insérées : " + docs.size());
        } else {
            System.out.println("[WARNING] Aucune musique insérée (doublons détectés)");
        }
    }


    public void updateOne(int id_musique, String nouveauTitre) {
        collection.updateOne(
            new Document("id_musique", id_musique),
            new Document("$set", new Document("titre", nouveauTitre))
        );
    }


    public void updateMany(int ancienneDuree, int nouvelleDuree) {
        collection.updateMany(
            new Document("duree", ancienneDuree),
            new Document("$set", new Document("duree", nouvelleDuree))
        );
    }


    
    public void deleteOne(int id_musique) {
        collection.deleteOne(new Document("id_musique", id_musique));
    }


    public void deleteMany(int duree) {
        collection.deleteMany(new Document("duree", duree));
    }
    private Document toDocument(Musique m) {
        return new Document("id_musique", m.getId_musique())
            .append("titre", m.getTitre())
            .append("duree", m.getDuree());
    }
}
