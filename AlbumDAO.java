import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumDAO {
    private final MongoCollection<Document> collection;

    public AlbumDAO(MongoDatabase db) {
        this.collection = db.getCollection("albums");
    }

    public void insertOne(Album album) {
        collection.insertOne(toDocument(album));
        System.out.println("Album inséré : " + album.getTitre());
    }

    public void insertMany(List<Album> albums) {
        List<Document> docs = albums.stream().map(this::toDocument).collect(Collectors.toList());
        collection.insertMany(docs);
        System.out.println("Albums insérés : " + albums.size());
    }

    public void updateOne(int id_album, String nouveauTitre) {
        collection.updateOne(
            new Document("id_album", id_album),
            new Document("$set", new Document("titre", nouveauTitre))
        );
    }
    public void updateMany(String date, String nouveauTitre) {
        collection.updateMany(
            new Document("date_sortie", date),
            new Document("$set", new Document("titre", nouveauTitre))
        );
    }

    public void deleteOne(int id_album) {
        collection.deleteOne(new Document("id_album", id_album));
    }

    public void deleteMany(String date_sortie) {
        collection.deleteMany(new Document("date_sortie", date_sortie));
    }

    private Document toDocument(Album album) {
        return new Document("id_album", album.getId_album())
                .append("titre", album.getTitre())
                .append("date_sortie", album.getDate_sortie())
                .append("artiste_id", album.getArtiste_id())
                .append("musiques", album.getMusiques());
    }
}
