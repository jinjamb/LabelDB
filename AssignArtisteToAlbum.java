import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;

public class AssignArtisteToAlbum {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("labeldb");

        MongoCollection<Document> albums = db.getCollection("albums");
        MongoCollection<Document> artistes = db.getCollection("artistes");
        List<Integer> artisteIds = artistes.find()
                .map(doc -> doc.getInteger("id_artiste"))
                .into(new ArrayList<>());

        if (artisteIds.isEmpty()) {
            System.out.println("Aucun artiste trouvé. Abandon.");
            return;
        }

        Random rand = new Random();

        for (Document album : albums.find()) {
            int albumId = album.getInteger("id_album");
            int randomArtisteId = artisteIds.get(rand.nextInt(artisteIds.size()));
            Document update = new Document("$set", new Document("artiste_id", randomArtisteId));
            albums.updateOne(new Document("id_album", albumId), update);
            System.out.println("Album " + albumId + " lié à l'artiste " + randomArtisteId);
        }
        mongoClient.close();
    }
}

