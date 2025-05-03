import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;

public class AssignMusiquesToAlbums {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("labeldb");

        MongoCollection<Document> albums = db.getCollection("albums");
        MongoCollection<Document> musiques = db.getCollection("musique");
        List<Integer> musiqueIds = musiques.find()
                .map(doc -> doc.getInteger("id_musique"))
                .into(new ArrayList<>());

        if (musiqueIds.isEmpty()) {
            System.out.println("Aucune musique trouvée.");
            return;
        }

        Random rand = new Random();
        for (Document album : albums.find()) {
            int idAlbum = album.getInteger("id_album");
            int nbMusiques = 3 + rand.nextInt(4);
            Set<Integer> musiquesAssignées = new HashSet<>();
            while (musiquesAssignées.size() < nbMusiques) {
                musiquesAssignées.add(musiqueIds.get(rand.nextInt(musiqueIds.size())));
            }
            List<Integer> listeFinale = new ArrayList<>(musiquesAssignées);
            Document update = new Document("$set", new Document("musiques", listeFinale));
            albums.updateOne(new Document("id_album", idAlbum), update);
            System.out.println("Album " + idAlbum + " reçoit musiques : " + listeFinale);
        }
        mongoClient.close();
    }
}

