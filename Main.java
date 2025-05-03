import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;



public class Main {
    public static void main(String[] args) {

        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("labeldb");

        GroupeDAO groupeDAO = new GroupeDAO(db);

        Groupe g1 = new Groupe(301, "Pulse Nova", "Pop");
        Groupe g2 = new Groupe(302, "Black Echo", "Rock");

        groupeDAO.insertOne(g1);
        groupeDAO.insertMany(List.of(g2));

        groupeDAO.updateOne(301, "Pulse Nova Deluxe");
        groupeDAO.updateMany("Rock", "Renamed Rock Group");

        groupeDAO.deleteOne(302);
        groupeDAO.deleteMany("Rock");

        /* PRODUCTEUR DAO */
        /*ProducteurDAO producteurDAO = new ProducteurDAO(db);

        Producteur p1 = new Producteur(401, "Thomas Garnier", "IndieProd");
        Producteur p2 = new Producteur(402, "Emma Michel", "StudioNova");

        producteurDAO.insertOne(p1);
        producteurDAO.insertMany(List.of(p2));

        producteurDAO.updateOne(401, "IndieProd Updated");
        producteurDAO.updateMany("Emma Michel", "NovaMedia");

        producteurDAO.deleteOne(401);
        producteurDAO.deleteMany("Emma Michel");*/

        /* TESTS MANAGER DAO
        /*ManagerDAO managerDAO = new ManagerDAO(db);

        Manager m1 = new Manager(301, "Lucie Dubois", "lucie@label.com");
        m1.setProducteurs(List.of(501, 502));

        managerDAO.insertOne(m1);
        managerDAO.updateOne(301, "lucie@nouvelmail.fr");
        managerDAO.deleteOne(301);*/

        /* TESTS MUSIQUE DAO
        /*MusiqueDAO musiqueDAO = new MusiqueDAO(db);

        Musique m1 = new Musique(5000, "Intro", 120);
        Musique m2 = new Musique(5001, "Outro", 180);

        // Insertion
        musiqueDAO.insertOne(m1);
        musiqueDAO.insertMany(List.of(m2));

        // Mise à jour
        musiqueDAO.updateOne(5000, "Introduction");
        musiqueDAO.updateMany(180, 200);

        // Suppression
        musiqueDAO.deleteOne(5000);
        musiqueDAO.deleteMany(200);*/


        /* TESTS ALBUMS DAO
        AlbumDAO albumdao = new AlbumDAO(db);

        System.out.println("[CHECK] Insertion d’un album...");
        Album a1 = new Album(999, "Test Album 1", "2025-05-01");
        a1.setArtiste_id(1);
        a1.setMusiques(List.of(10, 11, 12));
        albumdao.insertOne(a1);

        System.out.println("[CHECK] Insertion de plusieurs albums...");
        Album a2 = new Album(1000, "Test Album 2", "2025-05-02");
        a2.setArtiste_id(2);
        a2.setMusiques(List.of(13, 14));

        Album a3 = new Album(1001, "Test Album 3", "2025-05-02");
        a3.setArtiste_id(3);
        a3.setMusiques(List.of(15));

        albumdao.insertMany(List.of(a2, a3));

        System.out.println("[CHECK] Mise à jour du titre d’un album (id_album 999)...");
        albumdao.updateOne(999, "Album Renommé");

        System.out.println("[CHECK] Mise à jour en masse (tous les albums sortis le 2025-05-02)...");
        albumdao.updateMany("2025-05-02", "Album Modifié Groupé");

        System.out.println("[CHECK] Suppression d’un seul album (id_album 1001)...");
        albumdao.deleteOne(1001);

        System.out.println("[CHECK] Suppression de tous les albums sortis le 2025-05-02...");
        albumdao.deleteMany("2025-05-02");

        client.close();
        System.out.println("[FINISHED] Tests terminés.");
        */
    }
}

/*
        StatistiquesServices service = new StatistiquesServices(db);

        // Appels de méthodes à tester pour les statistiques 
        service.afficherAlbumsParArtiste();
        service.compterArtistesParGenre();
        service.listeManagersAvecArtistes();
        service.afficherMusiquesParGenreDeGroupe();
        service.rechercherAlbumsAvecPlusDe5Musiques();

        mongoClient.close();
    }*/

