import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;
import java.util.stream.Collectors;

public class ArtisteDAO {

    private final MongoCollection<Document> collection;

    public ArtisteDAO(MongoDatabase db) {
        this.collection = db.getCollection("artistes");
    }

    public void insertOne(Artiste a) {
        collection.insertOne(toDocument(a));
        System.out.println("Inséré : " + a.getNom());
    }

    public void insertMany(List<Artiste> liste) {
        List<Document> docs = liste.stream().map(this::toDocument).collect(Collectors.toList());
        collection.insertMany(docs);
        System.out.println("Insérés : " + docs.size() + " artistes");
    }

    public void updateOne(int id_artiste, String nouveauNom) {
        collection.updateOne(
            new Document("id_artiste", id_artiste),
            new Document("$set", new Document("nom", nouveauNom))
        );
    }

    public void updateMany(String genre, String nouveauGenre) {
        collection.updateMany(
            new Document("genre_musical", genre),
            new Document("$set", new Document("genre_musical", nouveauGenre))
        );
    }

    public void deleteOne(int id_artiste) {
        collection.deleteOne(new Document("id_artiste", id_artiste));
    }

    public void deleteMany(String genre) {
        collection.deleteMany(new Document("genre_musical", genre));
    }

    private Document toDocument(Artiste a) {

        Document d = new Document("id_artiste", a.getId_artiste()).append("nom", a.getNom()).append("genre_musical", a.getGenre_musical()).append("date_naissance", a.getDate_naissance());
        if (a.getGroupe() != null) {
            d.append("groupe", a.getGroupe());
        }
        if (a.getManager_id() != null) {
            d.append("manager_id", a.getManager_id());
        }
        return d;
        
    }
}
