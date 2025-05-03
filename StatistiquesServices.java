import java.util.List;
import java.util.Arrays;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;

public class StatistiquesServices {
    private final MongoDatabase db;

    public StatistiquesServices(MongoDatabase db) {
        this.db = db;
    }

    public void afficherAlbumsParArtiste() {
        MongoCollection<Document> artistes = db.getCollection("artistes");

        List<Document> pipeline = Arrays.asList(
            new Document("$lookup", new Document()
                .append("from", "albums")
                .append("localField", "id_artiste")
                .append("foreignField", "artiste_id")
                .append("as", "albums")),
            new Document("$limit", 5)
        );

        AggregateIterable<Document> result = artistes.aggregate(pipeline);

        result.forEach(doc -> {
            System.out.println("Artiste : " + doc.getString("nom"));
            List<Document> albums = (List<Document>) doc.get("albums");
            for (Document album : albums) {
                System.out.println(" - Album : " + album.getString("titre"));
            }
        });
    }

    public void compterArtistesParGenre() {
        MongoCollection<Document> artistes = db.getCollection("artistes");

        List<Document> pipeline = Arrays.asList(
            new Document("$group", new Document("_id", "$genre_musical")
                .append("total", new Document("$sum", 1))),
            new Document("$sort", new Document("total", -1)),
            new Document("$limit", 5)
        );

        artistes.aggregate(pipeline).forEach(doc ->
            System.out.println(doc.getString("_id") + " : " + doc.getInteger("total"))
        );
    }

    public void listeManagersAvecArtistes() {
        MongoCollection<Document> managers = db.getCollection("manager");

        List<Document> pipeline = Arrays.asList(
            new Document("$lookup", new Document()
                .append("from", "artistes")
                .append("localField", "id_manager")
                .append("foreignField", "manager_id")
                .append("as", "artistes")),
            new Document("$unwind", new Document("path", "$artistes")
                .append("preserveNullAndEmptyArrays", false)),
            new Document("$project", new Document("nom", 1)
                .append("artistes.nom", 1)),
            new Document("$limit", 5)
        );

        managers.aggregate(pipeline).forEach(doc -> {
            System.out.println("Manager : " + doc.getString("nom")
                + " → Artiste : " + ((Document) doc.get("artistes")).getString("nom"));
        });
    }

    public void afficherMusiquesParGenreDeGroupe() {
        MongoCollection<Document> musiques = db.getCollection("musique");

        List<Document> pipeline = Arrays.asList(
            new Document("$lookup", new Document()
                .append("from", "artistes")
                .append("localField", "artiste_id")
                .append("foreignField", "id_artiste")
                .append("as", "artiste")),
            new Document("$unwind", "$artiste"),
            new Document("$lookup", new Document()
                .append("from", "groupes")
                .append("localField", "artiste.groupe")
                .append("foreignField", "id_groupe")
                .append("as", "groupe")),
            new Document("$unwind", "$groupe"),
            new Document("$group", new Document("_id", "$groupe.genre_musical")
                .append("musiques", new Document("$addToSet", "$titre"))),
            new Document("$limit", 5)
        );

        musiques.aggregate(pipeline).forEach(doc -> {
            System.out.println("Genre : " + doc.getString("_id"));
            List<String> titres = (List<String>) doc.get("musiques");
            titres.forEach(titre -> System.out.println(" - " + titre));
        });
    }

    public void rechercherAlbumsAvecPlusDe5Musiques() {
        MongoCollection<Document> albums = db.getCollection("albums");

        List<Document> pipeline = Arrays.asList(
            new Document("$match", new Document("$expr",
                new Document("$gt", Arrays.asList(new Document("$size", "$musiques"), 5)))),
            new Document("$limit", 5)
        );

        albums.aggregate(pipeline).forEach(doc -> {
            System.out.println("Album : " + doc.getString("titre")
                + " → Nb musiques : " + ((List<?>) doc.get("musiques")).size());
        });
    }
}
